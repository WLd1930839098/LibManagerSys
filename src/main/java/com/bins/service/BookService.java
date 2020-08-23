package com.bins.service;

import com.bins.bean.Book;
import com.bins.bean.BookStoreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAll(String query, Pageable pageable);

    Book findById(int id);

    void add(Book book);

    void add(Book book,int sum);

    void delete(int id);

    Page<BookStoreItem> getStoreItems(Pageable pageable);
}
