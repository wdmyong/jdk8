package com.wdm.test.strategy;

/**
 * Created by wdmyong on 2017/8/10.
 */
public class Validator {

    public final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
        strategy = v;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

    public static void main(String[] args) {
        Validator validator = new Validator(new IsNumeric());
        System.out.println(validator.validate("1234"));
        validator = new Validator(new IsAllLoweCase());
        System.out.println(validator.validate("abcd"));

        // jdk8 lambda
        validator = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(validator.validate("abcd"));
        validator = new Validator(s -> s.matches("\\d+"));
        System.out.println(validator.validate("1234"));
    }
}
