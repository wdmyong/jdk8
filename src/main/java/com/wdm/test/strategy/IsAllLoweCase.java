package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/10.
 */
public class IsAllLoweCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
