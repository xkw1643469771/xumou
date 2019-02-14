package com.xumou.test.function.nio;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 */
public class ClientTest {

    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void socketSend() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8888);
        socket.getOutputStream().write("123123".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileCopyUtils.copy(socket.getInputStream(), bos);
        System.out.println(new String(bos.toByteArray()));
    }

    @Test
    public void restTemplatePost(){
        Map map = new HashMap();
        for (int i = 0; i < 10000; i++) {
            map.put(Math.random(),Math.random());
        }
        restTemplate.postForObject("http://localhost:8888/",map,String.class);
    }

    @Test
    public void uploadFile(){
        HttpHeaders headers = new HttpHeaders();// header设置
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // 文件上传类型
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>(); // 参数
        paramMap.add("msg","123");
        FileSystemResource file = new FileSystemResource(new File("E:/a.png"));
        paramMap.add("file",file); // 上传文件
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);
        restTemplate.exchange("http://localhost:8888/",HttpMethod.POST,requestEntity,String.class);
    }

    @Test
    public void test3() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bos);
        data.writeInt(1234567890);
        System.out.println(Arrays.toString(bos.toByteArray()));
    }

    @Test
    public void socketSendInt() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8888);
        Random r = new Random();
        byte[] bs = new byte[]{73, -106, 2, -46};
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < bs.length; i++) {
                socket.getOutputStream().write(new byte[]{bs[i%bs.length]});
                if(i % 6 == 0){
                    Thread.sleep(1000);
                }
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileCopyUtils.copy(socket.getInputStream(), bos);
        System.out.println(new String(bos.toByteArray()));
    }

    @Test
    public void test() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8888);
        socket.getOutputStream().write("1\n".getBytes());
    }


}