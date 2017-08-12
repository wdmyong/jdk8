package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class TailTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return input + "<label>provided by wdmyong</label></body></html>";
    }
}
