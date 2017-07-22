package com.wdm.test.book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by wdmyong on 2017/7/21.
 */
public class BookTest {

    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();

        Supplier<Book> supplier = Book::new; //() -> new Book();
        printBookAndInsertToList(supplier.get(), bookList);

        Function<Integer, Book> function = Book::new; //(Integer i) -> new Book(i);
        printBookAndInsertToList(function.apply(1), bookList);

        BiFunction<Integer, Integer, Book> bookBiFunction = Book::new; //(Integer i, Integer j) -> new Book(i, j);
        printBookAndInsertToList(bookBiFunction.apply(20, 30), bookList);

        ThreeFunction<Integer, Integer, Integer, Book> threeFunction = Book::new; //(i, j, k) -> new Book(i, j, k);
        printBookAndInsertToList(threeFunction.apply(4, 500, 600), bookList);

        FourFunction<Integer, Integer, Integer, String, Book> fourFunction = Book::new; //(i, j, k, s) -> new Book(i, j, k, s);
        printBookAndInsertToList(fourFunction.apply(7000, 80, 9000, "mybook"), bookList);

        bookList.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        bookList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        bookList.sort(Comparator.comparing((o) -> o.getPages()));
        bookList.sort(Comparator.comparing(Book::getNumber).reversed().thenComparing(Book::getPages));
        bookList.forEach((o) -> printBook(o));
    }

    public static void printBookAndInsertToList(Book book, List<Book> bookList) {
        bookList.add(book);
        printBook(book);
    }

    public static void printBook(Book book) {
        System.out.println(book.toString());
    }
}
