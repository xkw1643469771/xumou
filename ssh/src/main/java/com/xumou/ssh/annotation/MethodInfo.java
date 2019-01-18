package com.xumou.ssh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解指定在方法上, 用于测量一个方法的总执行时间
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodInfo {

    // 方法参数
    boolean param() default false;
    // 方法执行时间
    boolean time() default false;
}
