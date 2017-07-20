package com.wdm.test.jdk5;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wdmyong on 2017/7/20.
 */
public class GenericInterfaceTest {

    public static void main(String[] args) {
        Man man = new Man();
        ParameterizedType type = (ParameterizedType) man.getClass().getGenericInterfaces()[0];
        System.out.println(type.getActualTypeArguments()[0]);
    }
}
