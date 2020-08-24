package com.bins.service;

import com.bins.bean.Record;
import com.bins.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface RecordService {
    Page<Record> findAll(Pageable pageable);

    Page<Record> findAllByUser(User user, Pageable pageable);

    Page<Record> findAllBorrowedByUser(User user,Pageable pageable);

    Page<Record> findAllBorrowByUser(User user,Pageable pageable);

    Page<Record> findAllReturnByUser(User user,Pageable pageable);

    Page<Record> findAllByUserByBook(String userName, String bookName, Pageable pageable);

    Page<Record> searchByUserName(String userName, Pageable pageable);

    Page<Record> searchByBookName(String bookName, Pageable pageable);
}
