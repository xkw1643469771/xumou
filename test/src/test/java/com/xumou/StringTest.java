package com.xumou;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class StringTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String s1 = "我5\\uD852\\uDE4不信";
        System.out.println(new String(s1.getBytes("UTF-8")));
    }



}