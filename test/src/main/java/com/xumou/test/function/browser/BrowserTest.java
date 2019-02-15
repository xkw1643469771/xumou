package com.xumou.test.function.browser;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
public class BrowserTest {

    // 使用默认浏览器打开指定网址
    @Test
    public void openDefaultBrowser() throws URISyntaxException, IOException {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if(desktop.isSupported(Desktop.Action.BROWSE)){
                desktop.browse(new URI("https://www.baidu.com/"));
            }
        }
    }

    @Test
    public void test(){
        String result = new RestTemplate().getForObject("http://localhost:7438/", String.class);
        System.out.println(result);
    }


}