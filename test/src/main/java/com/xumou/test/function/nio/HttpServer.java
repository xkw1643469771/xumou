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
            threadPool = Executors.newFixedThreadPool(16);
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

    private static void accept() throws IOException {
        //System.out.println("===========================监听================================");
        SocketChannel sc = server.accept();
        System.out.println("监听" + sc);
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
    }



    private static void write(SelectionKey key) throws IOException {
        //System.out.println("===========================写出================================");
        SocketChannel sc = (SocketChannel) key.channel();
        System.out.println("写出" + sc);
        sc.configureBlocking(false);
        String str = "HTTP/1.1 200 OK\n" +
                "Content-Type: application/json;charset=utf-8\n\n"+
                objectMapper.writeValueAsString(Math.random());
        ByteBuffer src = ByteBuffer.wrap(str.getBytes());
        sc.write(src);
        sc.close();
    }

    private static void read(SelectionKey key) throws IOException {
        //System.out.println("===========================读取================================");
        SocketChannel sc = (SocketChannel) key.channel();
        sc.configureBlocking(false);
        readSc(sc);
        threadPool.execute(() -> {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("123 ");
                sb.append("HTTP/1.1 200 OK").append("\n");
                sb.append("Content-Type: application/json;charset=utf-8").append("\n");
                sb.append("\n");
                ByteBuffer src = ByteBuffer.wrap(sb.toString().getBytes());
                sc.write(src);
                sc.write(ByteBuffer.wrap(objectMapper.writeValueAsString(Math.random()).getBytes()));
                for (int i = 0; i < 20; i++) {
                    sleep(1000);
                    System.out.println(Thread.currentThread());
                    sc.write(ByteBuffer.wrap(("\n"+objectMapper.writeValueAsString(Math.random())).getBytes()));
                }
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static boolean readSc(SocketChannel sc) throws IOException {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (true) {
                int len = sc.read(byteBuffer);
                if (len == -1) {
                    sc.close();
                    //System.out.println("关闭的通道");
                    return false;
                }
                if (len == 0) {
                    break;
                }
                byteBuffer.flip();
                bos.write(byteBuffer.array(), 0, byteBuffer.limit());
                byteBuffer.clear();
            }
            byte[] bytes = bos.toByteArray();
            System.out.println(new String(bytes));
            return bytes.length > 0;
        }catch (Exception e){
            sc.close();
            throw e;
        }
    }

    public static void sleep(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
