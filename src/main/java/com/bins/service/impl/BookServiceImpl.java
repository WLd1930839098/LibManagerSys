package com.bins.service.impl;

import com.bins.bean.Book;
//import com.bins.bean.BookStoreItem;
import com.bins.bean.BookStoreItem;
import com.bins.dao.BookDao;
import com.bins.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    @Override
    public Page<Book> findAll(String query, Pageable pageable) {
        return bookDao.findByQuery("%" + query + "%", pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.getOne(id);
    }

    @Override
    public void add(Book book) {
        bookDao.save(book);
    }

    @Override
    public void add(Book book, int sum) {
        String name = book.getName();
        boolean free = book.isFree();
        for (int i = 0; i < sum; i++) {
            Book tmp = new Book();
            tmp.setName(name);
            tmp.setFree(free);
            bookDao.save(tmp);
        }
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Page<BookStoreItem> getBookStoreItems(Pageable pageable) {
        List<BookStoreItem> items = bookDao.findBookStoreItem();
        int size = pageable.getPageSize();
        int total = items.size();
        int fromIndex = size * pageable.getPageNumber();
        int toIndex = fromIndex + size;
        if (toIndex >= total) {
            toIndex = total;
        }
        List<BookStoreItem> content = items.subList(fromIndex,toIndex);
        Page<BookStoreItem> page = new PageImpl<BookStoreItem>(content, pageable, total);
        return page;
    }


}
