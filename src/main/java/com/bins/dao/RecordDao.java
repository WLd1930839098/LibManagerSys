package com.bins.dao;

import com.bins.bean.Record;
import com.bins.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordDao extends JpaRepository<Record,Long> {

    Page<Record> findAllByUser(User user, Pageable pageable);

    @Query("select r from Record r where r.user=?1 and r.book.free=false")
    Page<Record> findAllBorrowedByUser(User user, Pageable pageable);

    @Query("select r from Record r where r.user=?1 and r.borrowFlag=true")
    Page<Record> findAllBorrowByUser(User user,Pageable pageable);

    @Query("select r from Record r where r.user=?1 and r.borrowFlag=false")
    Page<Record> findAllReturnByUser(User user,Pageable pageable);

    @Query("select r from Record r where r.user.name like ?1 or r.book.name like ?2")
    Page<Record> findAllByQuery(String userName, String bookName,Pageable pageable);

    @Query("select r from Record r where r.user.name like ?1")
    Page<Record> searchByUserName(String userName, Pageable pageable);

    @Query("select r from Record r where r.book.name like ?1")
    Page<Record> searchByBookName(String bookName, Pageable pageable);
}
