package com.bins.service.impl;

import com.bins.bean.Record;
import com.bins.bean.User;
import com.bins.dao.RecordDao;
import com.bins.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Override
    public Page<Record> findAll(Pageable pageable) {
        return recordDao.findAll(pageable);
    }

    @Override
    public Page<Record> findAllByUser(User user, Pageable pageable) {
        return recordDao.findAllByUser(user, pageable);
    }

    @Override
    public Page<Record> findAllBorrowedByUser(User user, Pageable pageable) {
        return recordDao.findAllBorrowedByUser(user, pageable);
    }

    @Override
    public Page<Record> findAllBorrowByUser(User user, Pageable pageable) {
        return recordDao.findAllBorrowByUser(user, pageable);
    }

    @Override
    public Page<Record> findAllReturnByUser(User user, Pageable pageable) {
        return recordDao.findAllReturnByUser(user, pageable);
    }

    @Override
    public Page<Record> findAllByUserByBook(String userName, String bookName, Pageable pageable) {
        return recordDao.findAllByQuery("%" + userName + "%", "%" + bookName + "%", pageable);
    }

    @Override
    public Page<Record> searchByUserName(String userName, Pageable pageable) {
        return recordDao.searchByUserName("%" + userName + "%", pageable);
    }

    @Override
    public Page<Record> searchByBookName(String bookName, Pageable pageable) {
        return recordDao.searchByBookName("%" + bookName + "%", pageable);
    }


}
