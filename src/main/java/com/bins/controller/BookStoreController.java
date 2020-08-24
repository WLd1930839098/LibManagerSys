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
@RequestMapping("admin/bookStore")
public class BookStoreController {
    @Autowired
    private BookService bookService;

    @RequestMapping
    public String getBookStoreItems(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<BookStoreItem> page = bookService.getBookStoreItems(pageable);
        model.addAttribute("page", page);
        model.addAttribute("isAdd", true);

        return "admin/books-store";
    }

    @RequestMapping("toInput/{name}")
    public String toInput(@PathVariable String name, Model model) {
        if (name == null || name.equals("null")) {//新增图书
            model.addAttribute("bookStoreItem", new BookStoreItem());
        } else {
            BookStoreItem bookStoreItem = bookService.findStoreItemByName(name);
            model.addAttribute("bookStoreItem", bookStoreItem);
        }
        return "admin/books-store-input";
    }

    @RequestMapping("add")
    public String add(BookStoreItem bookStoreItem, Integer bookNum) {
        Book book = new Book();
        book.setName(bookStoreItem.getName());
        bookService.add(book, bookNum);
        return "redirect:/admin/bookStore";
    }

    @RequestMapping("delete/{name}")
    public String delete(@PathVariable String name) {
        bookService.deleteByName(name);
        return "redirect:/admin/bookStore";
    }
}
