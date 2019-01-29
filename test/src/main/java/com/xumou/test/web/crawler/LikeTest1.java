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
import java.util.regex.Pattern;

/**
 *
 */
public class LikeTest1 {

    RestTemplate restTemplate = new RestTemplate();
    Pattern number = Pattern.compile("[\\w]+");
    String url = "http://www.bteat.com";

    /**
     * 使用JSoup解析html
     */
    @Test
    public void test1(){
        String search = "冷";
        int sum = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String searchUrl = url + "/search/" + search + "-first-asc-"+(i+1);
            Document searchDoc = getForString(searchUrl);
            int count = parseHtml(searchDoc);
            sum += count;
            if(count == 0){
                break;
            }
        }
        System.out.println(sum);
    }

    public int parseHtml(Document searchDoc){
        int count = 0;
        for (Element el : searchDoc.select("a")) {
            String href = el.attr("href");
            if(href.contains("magnet")){
                String name = el.parent().parent().select(".item-list").text();
                System.out.println(name);
                System.out.println("\t\t"+href);
                count ++;
            }
        }
        return count;
    }

    @Test
    public void tet2(){
        System.out.println(Pattern.matches("","<a href=\"/search/苍-first-asc-5\">5</a>"));
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