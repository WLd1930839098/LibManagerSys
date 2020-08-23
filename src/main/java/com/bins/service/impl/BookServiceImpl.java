package com.bins.service.impl;

import com.bins.bean.Book;
//import com.bins.bean.BookStoreItem;
import com.bins.bean.BookStoreItem;
import com.bins.dao.BookDao;
import com.bins.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Book> findAll(String query, Pageable pageable){
        return bookDao.findByQuery("%"+query+"%",pageable);
    }

    @Override
    public Book findById(int id) {
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
        for(int i=0;i<sum;i++){
            Book tmp = new Book();
            tmp.setName(name);
            tmp.setFree(free);
            bookDao.save(tmp);
        }
    }

    @Override
    public void delete(int id) {
        bookDao.deleteById(id);
    }

    @Override
    public Page<BookStoreItem> getStoreItems(Pageable pageable) {
        Page<BookStoreItem> bookStoreItems =  (Page<BookStoreItem>)(BookStoreItem)bookDao.getStoreItems(pageable);
        return bookStoreItems;
    }
}
