package com.wdm.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by wdmyong on 2017/7/15.
 */
public class PredicateMain {
    public static void main(String[] args) {
        List<String> bookList = Arrays.asList("VC++", "C++ Primer", "Data Mining", "C++ Program Design");
        filterFirst(bookList, (str) -> ((String) str).startsWith("C"));
        filterSecond(bookList, (str) -> ((String) str).length() > 10);
    }

    public static void filterFirst(List<String> names, Predicate condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    public static void filterSecond(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }
}
