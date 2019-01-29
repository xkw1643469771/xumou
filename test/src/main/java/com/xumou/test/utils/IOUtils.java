package com.xumou.test.utils;

import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 */
public class IOUtils {

    private IOUtils(){}

    public static String readStrUTF8(InputStream is){
        return readStr(is, "UTF-8");
    }

    public static String readStrGBK(InputStream is){
        return readStr(is, "GBK");
    }

    public static String readStr(InputStream is, String charsetName){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileCopyUtils.copy(is, bos);
            return new String(bos.toByteArray(),charsetName);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}