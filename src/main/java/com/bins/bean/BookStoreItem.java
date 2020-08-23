package com.bins.bean;


public class BookStoreItem {
    private String name;
    private int bookSum;

    public BookStoreItem() {
    }

    public String getName() {
        return name;
    }

    public int getBookSum() {
        return bookSum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookSum(int bookSum) {
        this.bookSum = bookSum;
    }

    @Override
    public String toString() {
        return "BookStoreItem{" +
                "name='" + name + '\'' +
                ", bookSum=" + bookSum +
                '}';
    }
}
