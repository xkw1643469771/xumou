package com.xumou.ssh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimerAspect {
	
	/**
	 * 测试一个方法执行的时间, 通过 @MethodTimer 指定
	 */
    @Around("@annotation(com.xumou.ssh.annotation.MethodTimer)")
	public Object mthodAround(ProceedingJoinPoint method) throws Throwable{
    	long startTime = System.currentTimeMillis();
    	Logger logger = LoggerFactory.getLogger(method.getTarget().getClass());
    	Object object = method.proceed();
    	long endTime = System.currentTimeMillis();
    	logger.info(method.getSignature().getName() + "() 方法用时 " + (endTime-startTime) + " 毫秒");
		return object;
	}
	
}
