package com.xumou.test.web;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * 类似于JQuery的java类库, 用来解析html
 */
public class JSoupTest {

    public static final int TIME_OUT = 100;

    /**
     * 解析html, 获取指定标签
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        URL url = new URL("https://www.baidu.com/");
        Document doc = Jsoup.parse(url, TIME_OUT);
        System.out.println(doc.select("#lg"));
    }

    /**
     * 发送请求解析
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        Document document = Jsoup.connect("https://www.baidu.com/")
                .method(Connection.Method.GET)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36")
                .get();
        System.out.println(document);
    }

}