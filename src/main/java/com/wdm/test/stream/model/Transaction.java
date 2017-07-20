package com.wdm.test.stream.model;

/**
 * Created by duanyong on 2017/7/20.
 */
public class Transaction {

    public static final int GROCERY = 0;
    public static final int OTHER = 1;

    private Integer id;
    private Integer type;
    private Integer value;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
