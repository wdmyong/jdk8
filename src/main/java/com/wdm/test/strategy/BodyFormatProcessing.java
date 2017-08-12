package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class BodyFormatProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "<div>" + input + "</div>";
    }
}
