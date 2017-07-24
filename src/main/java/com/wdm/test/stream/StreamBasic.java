package com.wdm.test.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.wdm.test.stream.model.Dish;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        Map<Dish.Type, List<Dish>> result = geneDishGroupByType(Dish.menu);
        printMap(result);

        List<String> dishNameList = Dish.menu.stream().
                                    filter(dish -> { System.out.println("filter:\t" + dish.getName()); return dish.getCalories() > 300; }).
                                    //sorted(comparing(Dish::getCalories)).
                                    map(dish -> {System.out.println("map:\t" + dish.getName()); return dish.getName();}). // map(Dish::getName).
                                    limit(3).collect(toList());
        System.out.println(dishNameList);
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 500){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.parallelStream()
                .filter(d -> d.getCalories() < 500)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    public static Map<Dish.Type, List<Dish>> geneDishGroupByType(List<Dish> dishes) {
        return dishes.stream().collect(groupingBy(Dish::getType));
    }

    public static void printMap(Map<Dish.Type, List<Dish>> map) {
        map.forEach(((type, dishes) -> {
            System.out.println(type + " :");
            dishes.forEach((e) -> System.out.println(e));
            System.out.println("----------------");
        }));
    }
}
