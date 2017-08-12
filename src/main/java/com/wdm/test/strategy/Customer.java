package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class Customer {

    private int id;
    private String name;

    public Customer() {
        this(0, "default");
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
