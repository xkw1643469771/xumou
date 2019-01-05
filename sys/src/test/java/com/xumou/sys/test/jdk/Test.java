package com.xumou.sys.test.jdk;

import com.xumou.sys.test.pattern.po.User;

/**
 *
 */
public class Test {

    public static void main(String[] args) {
        test(() -> {
            System.out.println(123);
        });
    }

    public static void test(User user){
        user.study();
    }

}