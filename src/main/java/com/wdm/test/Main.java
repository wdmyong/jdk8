package com.wdm.test;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

/**
 * Created by wdmyong on 2017/7/14.
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("asdf123", "456asdf", "123456asdf");
        list.stream().map(DigestUtils::md5Hex).forEach(System.out::println);
        Range<Long> longList = Range.closed(1L, 10L).canonical(DiscreteDomain.longs());
        System.out.println(longList);
        Boolean flag = false;
        if (flag) {
            System.out.println("a");
        } else {
            System.out.println("b");
        }
        String s = "{a:b,\n         c:d\n,       d:[]}";
        System.out.println(StringUtils.deleteWhitespace(s));
        if (null == flag) {
            System.out.println("asdf");
        }
    }
}
