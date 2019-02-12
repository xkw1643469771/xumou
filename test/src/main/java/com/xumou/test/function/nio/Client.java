package com.xumou.test.function.nio;

import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        socket.getOutputStream().write("123123".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileCopyUtils.copy(socket.getInputStream(), bos);
        System.out.println(new String(bos.toByteArray()));
    }

}