package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class OnlineBankingImpl extends OnlineBanking {
    @Override
    void makeCustomerHappy(Customer customer) {
        System.out.println("Hello:\t" + customer.getName());
    }

    public static void main(String[] args) {
        OnlineBanking onlineBanking = new OnlineBankingImpl();
        onlineBanking.processCustomer(1);

        // jdk8
        onlineBanking.processCustomer(2, customer -> {
            System.out.println(customer.getName());
            System.out.println("\tWelcome to us");
        });
    }
}
