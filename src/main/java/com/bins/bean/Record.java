package com.bins.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column
    private boolean borrowFlag;


    public Long getId() {
        return id;
    }

    public void setBorrowFlag(boolean borrowFlag) {
        this.borrowFlag = borrowFlag;
    }

    public boolean isBorrowFlag() {
        return borrowFlag;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getTime() {
        return time;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
