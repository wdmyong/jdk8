package com.wdm.test.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanyong on 2017/8/11.
 */
public class SubjectImpl implements Subject {

    List<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers(String word) {
        observerList.forEach(observer -> observer.notify(word));
    }

    public static void main(String[] args) {
        SubjectImpl feed = new SubjectImpl();
        Observer hunanObserver = new HunanNews();
        Observer hubeiObserver = new HubeiNews();
        feed.addObserver(hunanObserver);
        feed.addObserver(hubeiObserver);

        // jdk8
        feed.addObserver(word -> {
            if (word.contains("test")) {
                System.out.println("test");
            }});
        feed.notifyObservers("hunan and hubei and test");
    }
}
