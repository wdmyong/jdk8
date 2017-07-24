package com.wdm.test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.wdm.test.stream.model.Dish;

/**
 * Created by wdmyong on 2017/7/23.
 */
public class StreamChapter5 {
    public static void main(String[] args) {
        //fun1();
        //fun2();
        //fun3();
        fun4();
    }

    public static void fun1() {
        List<Integer> integerList = Arrays.asList(1, 4, 3, 4, 5, 6, 4, 2, 9, 6, 90);
        List<Integer> result = integerList.stream().filter((e) -> e % 2 == 0).
                distinct().
                skip(2).
                limit(2).
                collect(Collectors.toList());
        System.out.println(result);
        integerList.stream().filter((e) -> e % 3 == 0).distinct().forEach(System.out::println);

        List<Dish> meatList = Dish.menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).
                limit(2).collect(Collectors.toList());
        System.out.println(meatList);
        meatList.stream().
                map(Dish::getName). //map(dish -> dish.getName()).
                map(String::length).
                forEach(System.out::println);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(n -> n * n).forEach(System.out::println);

        List<Integer> l1 = Arrays.asList(1, 3, 5);
        List<Integer> l2 = Arrays.asList(2, 4);
        List<int[]> pairs = l1.stream().flatMap(i -> l2.stream().
                map(j -> new int[]{i, j})).
                collect(Collectors.toList());
        List<int[]> pairs1 = l1.stream().flatMap(i -> l2.stream().
                filter(j -> (i + j) % 3 == 0).
                map(j -> new int[]{i, j})).
                collect(Collectors.toList());

        Arrays.asList(new int[]{1, 2, 3}).forEach(System.out::println);
        Arrays.asList(1, 2, 3).forEach(System.out::println);
        pairs.forEach(i -> System.out.println(i[0] + "," + i[1]));
        pairs1.forEach(i -> System.out.println(i[0] + "," + i[1]));
    }

    public static void fun2() {
        Optional<Dish> dish = Dish.menu.stream().filter(Dish::isVegetarian).
                                                //findAny()
                                                findFirst()
                                                ;
        System.out.println(dish.isPresent());
        dish.ifPresent(
                System.out::println //d -> {System.out.println(d);}
                );
        System.out.println(dish.get());
        Dish result = dish.orElse(new Dish("my fish", false, 1, Dish.Type.FISH));
        System.out.println(result);
    }

    public static void fun3() {
        List<Float> floatList = Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f);
        float result = floatList.stream().reduce(1.0f, (a, b) -> a * b);
        System.out.println(result);
        Optional<Integer> num = Dish.menu.stream().map(d -> 1).reduce(Integer::sum);
        num.ifPresent(System.out::println);
        System.out.println(Dish.menu.stream().count());
    }

    public static void fun4() {
        System.out.println(1.0f % 1 == 0);
        System.out.println(1.1f % 1 == 0);
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed().
                flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).
                        limit(10).map(t -> t[0]).
                        forEach(t -> System.out.print(t + " "));

        System.out.println();
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            } };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
