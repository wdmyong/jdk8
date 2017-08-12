package com.wdm.test.forkjoin;

import java.util.stream.LongStream;

/**
 * Created by wdmyong on 2017/7/27.
 */
public class Main {

    public static void main(String[] args) {
        long[] nums = LongStream.rangeClosed(1, 1000_000_00).toArray();
        System.out.println(System.currentTimeMillis());
        System.out.println(new ForkJoinSumCalculator(nums).compute());
        System.out.println(System.currentTimeMillis());
    }
}
