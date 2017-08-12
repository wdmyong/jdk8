package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class HunanNews implements Observer {
    @Override
    public void notify(String word) {
        if (word != null && word.contains("hunan")) {
            System.out.println(getClass());
        }
    }
}
