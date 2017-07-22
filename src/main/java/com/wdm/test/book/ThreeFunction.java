package com.wdm.test.book;

/**
 * Created by wdmyong on 2017/7/21.
 */
@FunctionalInterface
public interface ThreeFunction<T, U, X, R> {
    R apply(T t, U u, X x);
}
