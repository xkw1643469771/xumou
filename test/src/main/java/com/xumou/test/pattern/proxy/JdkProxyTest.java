package com.xumou.test.pattern.proxy;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Jdk 代理
 */
public class JdkProxyTest {

    /**
     * 生成一个子类代理对象
     */
    @Test
    public void jdkTest1(){
        User user  = (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理");
                return null;
            }
        });
        user.login();
    }

    /**
     * 生成两个对象, 一个用户, 一个代理
     */
    @Test
    public void jdkTest2() throws IOException {
        User user = new UserImpl();
        User userProxy  = (User) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理: " + proxy.getClass());
                return method.invoke(user, args);
            }
        });
        userProxy.login();
        System.out.println(userProxy.getClass());
        System.in.read();
    }

    static interface User{
        default void login(){
            System.out.println("登录");
        }
    }

    static class UserImpl implements User{}

}