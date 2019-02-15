package com.xumou;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 */
public class StringTest {

    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\a\\a.png");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        FileOutputStream fos = new FileOutputStream("D:\\a\\test.png");
        while(true){
            String s = br.readLine();
            if(s==null)
                break;
            int read = Integer.valueOf(s);
            System.out.println(read);
            fos.write(read);
        }
    }



}