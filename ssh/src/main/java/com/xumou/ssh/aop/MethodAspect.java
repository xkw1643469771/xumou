package com.xumou.ssh.aop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xumou.ssh.annotation.MethodInfo;
import com.xumou.ssh.annotation.MethodTimer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MethodAspect {

	private ObjectMapper objectMapper;

	public MethodAspect(){
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	/**
	 * 有指定注解时执行
	 * 测试一个方法执行的时间, 通过 @MethodTimer 指定
	 */
    @Around("@annotation(com.xumou.ssh.annotation.MethodTimer)")
	public Object mthodAround(ProceedingJoinPoint joinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    	try{
			return joinPoint.proceed();
		}finally{
			long endTime = System.currentTimeMillis();
			String className = joinPoint.getTarget().getClass().getSimpleName();
			String methodName = joinPoint.getSignature().getName();
			StringBuilder sb = new StringBuilder();
			sb.append(className).append(".").append(methodName).append("() 用时 ")
					.append(endTime-startTime).append(" 毫秒");
			logger.info(sb.toString());
		}
	}

	@Around("@annotation(com.xumou.ssh.annotation.MethodInfo)")
	public Object showMethodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		MethodInfo methodInfo = signature.getMethod().getAnnotation(MethodInfo.class);
		StringBuilder info = new StringBuilder();
		try {
			info.append(signature.getMethod().getName()).append(" : ");
			if(methodInfo.param()){
				Object[] args = joinPoint.getArgs();
				for (int i = 0; i < args.length; i++) {
					Object o = args[i];
					info.append("参数").append(i+1).append(" ");
					String str = objectMapper.writeValueAsString(o);
					info.append(str).append(" , ");
				}
				info.delete(info.length() - 3, info.length());
				info.append(" : ");
			}
			return joinPoint.proceed();
		}finally {
			if(methodInfo.time()){
				long endTime = System.currentTimeMillis();
				info.append("用时 ").append(endTime-startTime).append(" 毫秒 : ");
			}
			logger.info(info.toString().substring(0, info.length() - 3));
		}
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
