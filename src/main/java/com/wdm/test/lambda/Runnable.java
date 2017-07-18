package com.wdm.test.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wdmyong on 2017/7/14.
 * (params) -> expression
 * (params) -> statement
 * (params) -> { statements }
 */
public class Runnable {
    public static void main(String[] args) {
        new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                System.out.println("old Runnable");
            }
        }).start();
        new Thread(() -> System.out.println("jdk8 Runnable")).start();
    }
}
