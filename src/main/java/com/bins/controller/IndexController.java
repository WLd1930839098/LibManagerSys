package com.bins.controller;

import com.bins.bean.BookStoreItem;
import com.bins.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public String getBookStoreItems(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<BookStoreItem> page = bookService.getBookStoreItems(pageable);
        model.addAttribute("page", page);
        model.addAttribute("isAdd", true);

        return "user/books";
    }
}
