package com.bins.service.impl;

import com.bins.bean.Book;
//import com.bins.bean.BookStoreItem;
import com.bins.bean.BookStoreItem;
import com.bins.bean.Record;
import com.bins.bean.User;
import com.bins.dao.BookDao;
import com.bins.dao.RecordDao;
import com.bins.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RecordDao recordDao;

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
        book.setFree(true);
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void add(Book book, int sum) {
        String name = book.getName();
        boolean free = book.isFree();
        for (int i = 0; i < sum; i++) {
            Book tmp = new Book();
            tmp.setName(name);
            tmp.setFree(free);
            tmp.setFree(true);
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

    @Override
    public BookStoreItem findStoreItemByName(String name) {
        return bookDao.findStoreItemByName(name);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        List<Long> ids = bookDao.getIdsByName(name);
        bookDao.deleteBookByIdIn(ids);
    }

    @Override
    public Book getOneBookByName(String name) {
        List<Book> books = bookDao.findOneByName(name);
        if(books.isEmpty()){
            return null;
        }else{
            return books.get(0);
        }
    }

    @Override
    @Transactional
    public void borrow(Book book, User user) {
        book.setFree(false);
        bookDao.save(book);
        Record record = new Record();
        record.setBook(book);
        record.setUser(user);
        record.setTime(new Date());
        record.setBorrowFlag(true);
        recordDao.save(record);
    }

    @Override
    public Book getOneById(Long id) {
        return bookDao.getOne(id);
    }

    @Override
    @Transactional
    public void returnBook(Book book, User user) {
        Record record = new Record();
        record.setBook(book);
        record.setUser(user);
        record.setBorrowFlag(false);
        record.setTime(new Date());
        recordDao.save(record);
        book.setFree(true);
        bookDao.save(book);
    }


}
