package com.xumou.test.function.nio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class HttpServer {

    private static ExecutorService threadPool;
    private static ServerSocketChannel server;
    private static Selector selector;
    private static ObjectMapper objectMapper;

    static {
        try {
            threadPool = Executors.newFixedThreadPool(4);
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(8888));
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            objectMapper = new ObjectMapper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void accept() throws IOException {
        System.out.println("===========================监听================================");
        SocketChannel sc = server.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                selector.select(1);
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try{
                        if (key.isAcceptable())
                            accept();
                        else if (key.isReadable())
                            read(key);
                        else if (key.isWritable())
                            write(key);
                    }catch (Exception e){
                        close(key);
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(SelectionKey key) {
        if(key != null){
            try {
                key.channel().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write(SelectionKey key) throws IOException {
        System.out.println("===========================写出================================");
        SocketChannel sc = (SocketChannel) key.channel();
        sc.configureBlocking(false);
        String str = "HTTP/1.1 200 OK\n" +
                "Content-Type: application/json;charset=utf-8\n\n"+
                objectMapper.writeValueAsString(Math.random());
        ByteBuffer src = ByteBuffer.wrap(str.getBytes());
        sc.write(src);
        sc.close();
    }

    private static void read(SelectionKey key) throws IOException {
        System.out.println("===========================读取================================");
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (true) {
            int len = sc.read(byteBuffer);
            if (len == -1) {
                sc.close();
                System.out.println("关闭的通道");
                return;
            }
            if (len == 0) {
                break;
            }
            byteBuffer.flip();
            bos.write(byteBuffer.array(), 0, byteBuffer.limit());
            byteBuffer.clear();
        }
        byte[] bytes = bos.toByteArray();
        if (bytes.length > 0) {
            threadPool.execute(() -> {
                try {
                    System.out.println(new String(bytes));
                    Thread.sleep(10000);
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_WRITE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}