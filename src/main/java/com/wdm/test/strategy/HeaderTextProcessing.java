package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "<!DOCTYPE html><html lang=\"en\"><head><title>my web</title><style></style></head><body>" + input;
    }
}
