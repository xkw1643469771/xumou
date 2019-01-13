package com.xumou.ssh.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xumou.ssh.annotation.MethodTimer;

@Aspect
@Component
public class MethodAspect {
	
	/**
	 * 有指定注解时执行
	 * 测试一个方法执行的时间, 通过 @MethodTimer 指定
	 */
    @Around("@annotation(com.xumou.ssh.annotation.MethodTimer)")
	public Object mthodAround(ProceedingJoinPoint joinPoint) throws Throwable{
    	long startTime = System.currentTimeMillis();
    	Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    	Object object = joinPoint.proceed();
    	long endTime = System.currentTimeMillis();
    	logger.info(joinPoint.getSignature().getName() + "() 方法用时 " + (endTime-startTime) + " 毫秒");
		return object;
	}
    
    /**
     * 获取代理的方法
     * 通过获取代理方法自己实现指定注解代理
     */
    //@Around("execution(* com.xumou.ssh..*.*(..))")
    public Object custom(ProceedingJoinPoint joinPoint) throws Throwable{
    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    	Method method = signature.getMethod();
    	MethodTimer annotation = method.getAnnotation(MethodTimer.class);
    	if(annotation != null){
    		long startTime = System.currentTimeMillis();
        	Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    		Object proceed = joinPoint.proceed();
    		long endTime = System.currentTimeMillis();
        	logger.info(joinPoint.getSignature().getName() + "() 方法用时 " + (endTime-startTime) + " 毫秒");
    		return proceed;
    	}else{
    		return joinPoint.proceed();
    	}
    }
	
}
