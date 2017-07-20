package com.wdm.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.wdm.test.stream.model.Transaction;

import static java.util.stream.Collectors.toList;

/**
 * Created by duanyong on 2017/7/20.
 */
public class StreamTest {

    public static void main(String[] args) {
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));

        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).
        peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase).
        peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream.flatMap(childList -> childList.stream()).forEach(e -> System.out.println(e));

        Stream<String> stream = Stream.of("a", "b", "c");
        String[] strArray1 = stream.toArray(String[]::new);

        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        List<String> list1 = stream.collect(Collectors.toList());

        stream = Arrays.stream(strArray);
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));

        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
        stream.forEach(e -> System.out.println(e));

        Set<String> set1 = stream.collect(Collectors.toSet());
        Stack<String> stack1 = stream.collect(Collectors.toCollection(Stack::new));
        String str = stream.collect(Collectors.joining()).toString();

        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);



        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setId(i);
            transaction.setType(i % 2);
            transaction.setValue(new Random().nextInt());
            transaction.setDesc("");
            transactionList.add(transaction);
        }
        long now = System.currentTimeMillis();
        only4Ids(transactionList);//.forEach(id -> System.out.print(id));
        System.out.println(System.currentTimeMillis() - now);
        now = System.currentTimeMillis();
        only4IdsJdk8(transactionList);//.forEach(id -> System.out.print(id));
        System.out.println(System.currentTimeMillis() - now);
    }

    public static List<Integer> only4Ids(List<Transaction> transactionList) {
        List<Transaction> groceryTransactions = new ArrayList<>();
        for(Transaction t: transactionList){
            if(t.getType() == Transaction.GROCERY){
                groceryTransactions.add(t);
            }
        }
        Collections.sort(groceryTransactions, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2){
                return t2.getValue().compareTo(t1.getValue());
            }
        });
        List<Integer> transactionIds = new ArrayList<>();
        for(Transaction t: groceryTransactions){
            transactionIds.add(t.getId());
        }
        return transactionIds;
    }

    public static List<Integer> only4IdsJdk8(List<Transaction> transactionList) {
        return transactionList.stream().filter(transaction -> transaction.getType() == Transaction.GROCERY).
                sorted(Comparator.comparing(Transaction::getValue).reversed()).map(Transaction::getId).
                collect(toList());
    }

    public static void print(String text) {
        // Java 8
       Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
        System.out.println("old:\t" + text);
        }
    }
    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if (text != null) ? text.length() : -1;
    };
}
