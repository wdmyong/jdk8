package com.wdm.test.book;

/**
 * Created by wdmyong on 2017/7/21.
 */
@FunctionalInterface
public interface FourFunction<T, U, X, Y, R> {
    R apply(T t, U u, X x, Y y);
}
