package com.xumou.test.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 */
public class CGLibTest {

    /**
     * 通过CGLib代理, 属于代理模式
     */
    @Test
    public void cjlibTest() throws IOException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestObj.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("代理"+method.getName());
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        TestObj testObj = (TestObj) enhancer.create();
        testObj.a();
    }

    /**
     * 模拟AOP代理, 属于包装模式
     */
    @Test
    public void cjlibAopTest() throws IOException {
        TestObj testObj = new TestObj();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestObj.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("代理"+method.getName());
                Object result = method.invoke(testObj, objects);
                return result;
            }
        });
        TestObj testObjProxy = (TestObj) enhancer.create();
        testObjProxy.a();
    }

    public static class TestObj {
        public void a(){
            System.out.println("a");
            this.b();
        }
        public void b(){
            System.out.println("b");
        }
    }

}



