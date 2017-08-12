package com.wdm.test.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by wdmyong on 2017/7/27.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final int MIN_THRESHOLD = 100;
    private long[] nums;
    private int start;
    private int end;

    public ForkJoinSumCalculator(long[] nums) {
        this(nums, 0, nums.length);
    }

    private ForkJoinSumCalculator(long[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= MIN_THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(nums, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(nums, start + length / 2, end);
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += nums[i];
        }
        return result;
    }
}
