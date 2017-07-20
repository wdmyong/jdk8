package com.wdm.test.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by duanyong on 2017/7/19.
 */
public class poolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
        executorService.execute(() -> System.out.println("jdk8 test"));
    }
}
