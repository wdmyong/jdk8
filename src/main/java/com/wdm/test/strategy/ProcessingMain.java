package com.wdm.test.strategy;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class ProcessingMain {

    public static void main(String[] args) {
        ProcessingObject<String> head = new HeaderTextProcessing();
        ProcessingObject<String> body = new BodyFormatProcessing();
        ProcessingObject<String> tail = new TailTextProcessing();
        body.setSuccessor(head);
        head.setSuccessor(tail);
        System.out.println(body.handle("Hello World"));

        // jdk8
        UnaryOperator<String> bodyProcessing = (text) -> "<div>" + text + "</div>";
        UnaryOperator<String> headProcessing = (text) ->  "<!DOCTYPE html><html lang=\"en\"><head><title>my web</title><style></style></head><body>" + text;
        UnaryOperator<String> tailProcessing = (text) -> text + "<label>provided by wdmyong</label></body></html>";
        Function<String, String> pipeline = bodyProcessing.andThen(headProcessing).andThen(tailProcessing);
        System.out.println(pipeline.apply("Hello JDK8"));
    }
}
