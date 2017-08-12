package com.wdm.test.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdmyong on 2017/8/11.
 */
public interface Subject {

    void addObserver(Observer observer);

    void notifyObservers(String word);
}
