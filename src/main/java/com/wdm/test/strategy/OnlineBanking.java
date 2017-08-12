package com.wdm.test.strategy;

import java.util.function.Consumer;

/**
 * Created by wdmyong on 2017/8/11.
 */
public abstract  class OnlineBanking {

    public void processCustomer(int id) {
        Customer customer = new Customer();
        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Customer customer);

    // jdk8
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer customer = new Customer();
        makeCustomerHappy.accept(customer);
    }
}
