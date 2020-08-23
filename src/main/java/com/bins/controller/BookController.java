package com.bins.controller;

import com.bins.bean.Book;
import com.bins.bean.BookStoreItem;
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

@Controller
@RequestMapping("admin/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping
    public String findAll(@PageableDefault(size = 5,sort = {"id"},
    direction = Sort.Direction.ASC) Pageable pageable, Model model){
        Page<Book> page = bookService.findAll(pageable);
        model.addAttribute("page",page);
        return "admin/books";
    }

    @RequestMapping("search")
    public String search(@PageableDefault(size = 5,sort ={"id"},
    direction = Sort.Direction.DESC)Pageable pageable, String name, Model model){
        Page<Book> page = bookService.findAll(name,pageable);
        model.addAttribute("page",page);
        return "admin/book::booksList";
    }

    @RequestMapping("toInput/{id}")
    public String toInput(@PathVariable int id, Model model){
        if(id==-1){//新增图书
            model.addAttribute("book",new Book());
        }else{
            Book book = bookService.findById(id);
            model.addAttribute("book",book);
        }
        return "admin/books-input";
    }

    @RequestMapping("add")
    public String add(Book book,int sum){
        bookService.add(book,sum);
        return "redirect:/admin/book";
    }

    @RequestMapping("delete/{id}")
    public String toInput(@PathVariable int id){
        bookService.delete(id);
        return "redirect:/admin/book";
    }

    @RequestMapping("store")
    public String getBookStoreItems(@PageableDefault(size = 5,sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model){
        Page<BookStoreItem> page = bookService.getStoreItems(pageable);
        model.addAttribute("page",page);

        return "admin/books-store";
    }

}
