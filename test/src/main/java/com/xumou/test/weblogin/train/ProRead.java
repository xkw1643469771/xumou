package com.xumou.test.weblogin.train;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 *
 */
public class ProRead {

    private String str;
    private Map<String, Object> root;
    private ObjectMapper objectMapper;

    public ProRead(String str){
        this.str = str;
    }



}