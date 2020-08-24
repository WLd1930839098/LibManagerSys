package com.bins.controller;

import com.bins.bean.*;
import com.bins.service.BookService;
import com.bins.service.RecordService;
import com.bins.service.RoleService;
import com.bins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;


    @RequestMapping
    public String index() {
        return "redirect:/toLogin";
    }

    @RequestMapping("/books")
    public String getBookStoreItems(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            Page<BookStoreItem> page = bookService.getBookStoreItems(pageable);
            model.addAttribute("page", page);
            model.addAttribute("isAdd", true);

            return "user/books";
        }
    }

    @RequestMapping("records")
    public String getAllRecords(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            Page<Record> page = recordService.findAllByUser(user, pageable);
            model.addAttribute("page", page);
            return "user/records";
        }
    }

    @RequestMapping("records-borrow")
    public String getAllBorrowRecords(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            Page<Record> page = recordService.findAllBorrowByUser(user, pageable);
            model.addAttribute("page", page);
            return "user/records-borrow";
        }
    }

    @RequestMapping("records-return")
    public String getAllReturnRecords(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            Page<Record> page = recordService.findAllReturnByUser(user, pageable);
            model.addAttribute("page", page);
            return "user/records-return";
        }
    }

    @RequestMapping("mine")
    public String mine(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            model.addAttribute("user", user);
            return "user/mine";
        }
    }

    @RequestMapping("update")
    public String update(User user, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", user);
        userService.add(user);
        return "redirect:/books";
    }

    @RequestMapping("borrow/{name}")
    public String borrow(@PathVariable String name, HttpServletRequest request) {
        Book book = bookService.getOneBookByName(name);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {
            bookService.borrow(book, user);
            return "redirect:/books";
        }
    }

    @RequestMapping("return/{id}")
    public String returnBook(@PathVariable Long id, HttpServletRequest request) {
        Book book = bookService.getOneById(id);
        User user = (User) request.getSession().getAttribute("user");
        bookService.returnBook(book, user);
        return "redirect:/books";
    }

    @RequestMapping("myBorrow")
    public String myBorrow(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/toLogin";
        } else {

            Page<Record> page = recordService.findAllBorrowedByUser(user, pageable);
            model.addAttribute("page", page);
            return "user/my-borrow";
        }
    }
}
