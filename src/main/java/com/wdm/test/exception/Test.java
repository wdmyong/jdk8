package com.wdm.test.exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by wdmyong on 2017/8/11.
 */
public class Test {
    public static void main(String[] args) {
        try {
            readFile("");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            Throwable t = getStartCause(e);
            System.out.println(t);
            System.out.println(t.toString());
            System.out.println(t.getMessage());
        }
    }


    public static void readFile(String path) {
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("message", e);
        }
    }

    public static Throwable getStartCause(Throwable e) {
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e;
    }
}
