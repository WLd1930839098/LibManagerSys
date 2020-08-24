package com.bins.dao;

import com.bins.bean.Book;
import com.bins.bean.BookStoreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.name like ?1")
    Page<Book> findByQuery(String s, Pageable pageable);

    @Query("select  new com.bins.bean.BookStoreItem(b.name,count(b.id)) from Book b where b.free=true group by b.name")
    List<BookStoreItem> findBookStoreItem();

    @Query("select new com.bins.bean.BookStoreItem(b.name,count(b.id)) from Book b where b.name=?1 group by b.name")
    BookStoreItem findStoreItemByName(String name);


    @Query("select b.id from Book b where b.name=?1")
    List<Long> getIdsByName(String name);

//    @Query("delete from Book b where b.id in (?1)")
//    void deleteByIds(List<Long> ids);

    void deleteBookByIdIn(List<Long> ids);

    @Query("select b from Book b where b.name=?1 and b.free=true")
    List<Book> findOneByName(String name);
}
