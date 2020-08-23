package com.bins.bean;


public class BookStoreItem {

    private String name;
    private Long sum;



    public BookStoreItem() {
    }

    public BookStoreItem(String name, Long sum) {
        this.name = name;
        this.sum = sum;
    }


    public String getName() {
        return name;
    }

    public Long getSum() {
        return sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "BookStoreItem{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
