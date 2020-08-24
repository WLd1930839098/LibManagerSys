package com.bins.service;

import com.bins.bean.Book;
import com.bins.bean.BookStoreItem;
import com.bins.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findAll(String query, Pageable pageable);

    Book findById(Long id);

    void add(Book book);

    void add(Book book, int sum);

    void delete(Long id);

    Page<BookStoreItem> getBookStoreItems(Pageable pageable);

    BookStoreItem findStoreItemByName(String name);

    void deleteByName(String name);

    Book getOneBookByName(String name);

    void borrow(Book book, User user);
}
