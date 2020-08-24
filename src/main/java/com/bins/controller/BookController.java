package com.bins.controller;

import com.bins.bean.Book;
import com.bins.bean.User;
import com.bins.service.BookService;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("admin/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping
    public String findAll(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<Book> page = bookService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/books";
    }

    @RequestMapping("search")
    public String search(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, String name, Model model) {
        Page<Book> page = bookService.findAll(name, pageable);
        model.addAttribute("page", page);
        return "admin/book::booksList";
    }

    @RequestMapping("toInput/{id}")
    public String toInput(@PathVariable Long id, Model model) {
        if (id == -1) {//新增图书
            model.addAttribute("book", new Book());
        } else {
            Book book = bookService.findById(id);
            model.addAttribute("book", book);
        }
        return "admin/books-input";
    }

    @RequestMapping("add")
    public String add(Book book) {

        bookService.add(book);
        return "redirect:/admin/book";
    }

    @RequestMapping("delete/{id}")
    public String toInput(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/admin/book";
    }


    @RequestMapping("borrow/{name}")
    public String borrow(@PathVariable String name, HttpServletRequest request, HttpServletResponse response){
        Book book = bookService.getOneBookByName(name);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/toLogin";
        }else{
            bookService.borrow(book,user);
            return "redirect:/books";
        }
    }


}
