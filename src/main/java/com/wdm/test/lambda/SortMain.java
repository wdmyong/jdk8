package com.wdm.test.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wdmyong on 2017/7/14.
 */
public class SortMain {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("f", "b", "c", "d");
        printList(list);
        list.sort((a, b) -> a.compareTo(b));
        printList(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public static void printList(List<?> list) {
        list.forEach(e -> System.out.println(e));
        list.forEach(System.out::println);
    }
}
