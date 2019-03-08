package com.xumou.test.pool;

/**
 *
 */
public class User {

    public void add(){
        System.out.println("执行添加");
    }

    public void close(){
        System.out.println("关闭连接");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(User.class.getMethod("close").toGenericString());
    }

}