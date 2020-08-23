package com.bins.dao;

import com.bins.bean.Book;
import com.bins.bean.BookStoreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book,Integer> {
    @Query("select b from Book b where b.name like ?1")
    Page<Book> findByQuery(String s, Pageable pageable);

    @Query("select b.name,count(b) as num from Book b group by b.name")
    Page<BookStoreItem> getStoreItems(Pageable pageable);
}
