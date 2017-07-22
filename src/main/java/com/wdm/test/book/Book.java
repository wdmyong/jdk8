package com.wdm.test.book;

/**
 * Created by wdmyong on 2017/7/21.
 */
public class Book {
    private Integer number;
    private Integer pages;
    private Integer price;
    private String name;

    public Book() {
        this(0);
    }

    public Book(Integer number) {
        this(number, 0);
    }

    public Book(Integer number, Integer pages) {
        this(number, pages, 0);
    }

    public Book(Integer number, Integer pages, Integer price) {
        this(number, pages, price, "default");
    }

    public Book(Integer number, Integer pages, Integer price, String name) {
        this.number = number;
        this.pages = pages;
        this.price = price;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "number=" + number +
                ", pages=" + pages +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
