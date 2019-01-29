package com.xumou.test.web.train;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 */
public class TrainTicket {

    public <T> T getForObject(String url,HttpHeaders header, Class<T> responseType){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        if(header == null)
            header = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, header);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType).getBody();
    }

    @Test
    public void test1() throws IOException {
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?" +
                "leftTicketDTO.train_date=2019-02-08" +
                "&leftTicketDTO.from_station=BJP" +
                "&leftTicketDTO.to_station=TYV" +
                "&purpose_codes=ADULT";
        String cookie = "JSESSIONID=D72092789C4E1E331F0667B37A0502A; _gscu_1094754927=34235684l5zxl714; _trs_uv=jktgbarf_365_9npo";
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.COOKIE, cookie);
        header.setContentType(MediaType.APPLICATION_JSON);
        for (int i = 1; i <= 1; i++) {
           String body = getForObject(url, header, String.class);
           JsonNode jsonNode = readStr(body);
           System.out.println(body);
            Iterator<JsonNode> iterator = jsonNode.get("data").get("result").iterator();
            while(iterator.hasNext()){
                JsonNode next = iterator.next();
                String[] ss = next.asText().split("\\|");
                if(equalsQK(ss, "车次", "K961"))
                    System.out.println(getQK(ss, "无座"));
            }
       }
    }


    public JsonNode readStr(String str){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(str);
        } catch (IOException e) {
            System.out.println(str);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public String getQK(String[] strs, String name){
        Integer idx = resultMapping.get(name);
        if(idx == null) {
            return "";
        }else {
            return strs[idx];
        }
    }

    public boolean equalsQK(String[] strs, String name, String eqlVal){
        Integer idx = resultMapping.get(name);
        if(idx == null) {
            return false;
        }else {
            return strs[idx].equals(eqlVal);
        }
    }

    private static Map<String,Integer> resultMapping = new HashMap<>();
    static{
        resultMapping.put("车次", 3);
        resultMapping.put("软卧", 23);
        resultMapping.put("硬卧", 28);
        resultMapping.put("硬座", 29);
        resultMapping.put("无座", 26);
    }

}