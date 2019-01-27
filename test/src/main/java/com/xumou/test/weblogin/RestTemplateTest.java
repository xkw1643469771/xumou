package com.xumou.test.weblogin;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 */
public class RestTemplateTest {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * post基本方式请求
     */
    @Test
    public void postTest(){
        String url = "http://localhost:9998/userTest/selectUserByParam";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, header);
        String body = restTemplate.postForObject(url, httpEntity, String.class);
        System.out.println(body);
    }

    /**
     * 自定义请求
     */
    @Test
    public void executeTest(){
        String url = "http://localhost:9998/userTest/selectUserByParam";
        restTemplate.execute(url, HttpMethod.POST, new RestTemplate() {
            public RequestCallback getRequestCallback() {
                MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
                HttpHeaders header = new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, header);
                return super.httpEntityCallback(requestEntity, String.class);
            }
        }.getRequestCallback(), new ResponseExtractor<String>() {
            public String extractData(ClientHttpResponse response) throws IOException {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileCopyUtils.copy(response.getBody(), bos);
                System.out.println(new String(bos.toByteArray()));
                return "";
            }
        });
    }

    /**
     * 自定义请求2
     */
    @Test
    public void executeTest2(){
        String url = "http://localhost:9998/userTest/selectUserByParam";
        String str = new RestTemplate().execute(url, HttpMethod.POST, new RequestCallback() {
            public void doWithRequest(ClientHttpRequest request) throws IOException {
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                request.getBody().write("{\"id\":1}".getBytes());
            }
        }, new ResponseExtractor<String>() {
            public String extractData(ClientHttpResponse response) throws IOException {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileCopyUtils.copy(response.getBody(), bos);
                return new String(bos.toByteArray());
            }
        });
        System.out.println(str);
    }

    /**
     * 测试exchange
     */
    @Test
    public void exchange(){
        String url = "http://localhost:9998/userTest/selectUserByParam";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(requestEntity.getBody());
    }

    /**
     * get请求
     */
    @Test
    public void getTest(){
        String url = "http://localhost:8080/mis/es/toSingleReimb.action?functionId=11018124&emptyMsg=false";
        String str = restTemplate.getForObject(url, String.class);
        System.out.println(str);
    }

    /**
     * 模拟登陆后
     */
    @Test
    public void login(){
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?" +
                "leftTicketDTO.train_date=2019-02-08" +
                "&leftTicketDTO.from_station=CZF" +
                "&leftTicketDTO.to_station=BJP" +
                "&purpose_codes=ADULT";
        String cookie = "JSESSIONID=D72092789C4E1E331F0667B37A0502AD; _gscu_1094754927=34235684l5zxl714; _trs_uv=jktgbarf_365_9npo";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Cookie", cookie);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }
}