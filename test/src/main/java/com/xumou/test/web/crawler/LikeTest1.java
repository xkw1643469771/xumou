package com.xumou.test.web.crawler;

import com.xumou.test.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.function.Consumer;

/**
 *
 */
public class LikeTest1 {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * 使用JSoup解析html
     */
    @Test
    public void test1(){
        String url = "http://www.bteat.com";
        Document doc = getForString(url);
        String searchUrl = url + doc.select("form").attr("action");
        searchUrl += "?kw="+ URLEncoder.encode("");
        Document searchDoc = getForString(searchUrl);
        System.out.println(searchDoc);
    }

    public Document getForString(String url){
        String str = restTemplate.execute(url, HttpMethod.GET, new RequestCallback() {
            public void doWithRequest(ClientHttpRequest request) throws IOException {
                HttpHeaders headers = request.getHeaders();
                headers.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            }
        }, new ResponseExtractor<String>() {
            public String extractData(ClientHttpResponse response) throws IOException {
                return IOUtils.readStrUTF8(response.getBody());
            }
        });
        return Jsoup.parse(str);
    }

}