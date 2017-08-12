package com.wdm.test.stream;

import java.util.List;
import java.util.Map;

import com.wdm.test.stream.model.CaloricLevel;
import com.wdm.test.stream.model.Dish;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by wdmyong on 2017/7/27.
 */
public class Chapter8 {
    public static void main(String[] args) {
        fun();
    }

    public static void fun() {
        Map<CaloricLevel, List<Dish>> result = Dish.menu.stream().collect(groupingBy(Dish::getLevel));
        result.forEach((k, v) -> {
            System.out.print(k + ":\t");
            v.forEach(System.out::print);
            System.out.println();
        });
    }
}
