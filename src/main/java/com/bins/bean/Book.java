package com.bins.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private boolean free;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return free;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", free=" + free +
                '}';
    }
}
