package com.xumou.test.pool;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 */
public class UserPool {

    private Queue<User> closedUser;
    private Queue<User> usedUser;

    public UserPool(int size){
        closedUser = new ConcurrentLinkedQueue<>();
        usedUser = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < size; i++) {
            User user = new User();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(User.class);
            enhancer.setCallback(new UserMethodInterceptor(user));
            closedUser.add(user);
        }
    }

    public User getUser() {
        for (int i = 0; i < 5; i++) {
            if(!closedUser.isEmpty()){
                User user = closedUser.remove();
                usedUser.add(user);
                return user;
            }

        }
        throw new RuntimeException("无可用User");
    }

    private class UserMethodInterceptor implements MethodInterceptor {
        User user;
        public UserMethodInterceptor(User user){
            this.user = user;
        }
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if(method.toGenericString().equals("public void com.xumou.test.pool.User.close()")){
                usedUser.remove(user);
                closedUser.add(user);
                System.out.println("代理关闭");
                return null;
            }else{
                return method.invoke(user, objects);
            }
        }
    }

}