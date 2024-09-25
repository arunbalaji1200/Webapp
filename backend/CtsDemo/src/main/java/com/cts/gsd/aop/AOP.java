package com.cts.gsd.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOP {
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	public Logger getLog() {
		return log;
	}
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)" +
	        " || within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointCut() {
		
	}
	
	@Pointcut("within(com.cts.gsd.*)"+"||within(com.cts.gsd.controller.*)"
			+ " ||within(com.cts.gsd.user.*)||within(com.cts.gsd.model.*)"+
			"||within(com.cts.gsd.repository.*)")
	public void springPackagePointCut() {
		
	}
	@Around("springPackagePointCut() && springBeanPointCut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		log.debug("Method:"+joinPoint.getSignature().getName());
		return result;
    }
	
	@Before("springPackagePointCut() && springBeanPointCut()")
	public void logBefore(JoinPoint joinpoint) throws Throwable{
		log.debug("Method Before Executing:"+ joinpoint.getSignature().getName());
//		log.debug("Method Arugument:"+ joinpoint.getSignature().getDeclaringType());
	}
	
	@AfterReturning("springPackagePointCut() && springBeanPointCut()")
	public void logAfter(JoinPoint joinpoint) throws Throwable{
		log.debug("Method After Executing:"+ joinpoint.getSignature().getName());
		Arrays.stream(joinpoint.getArgs())
        .forEach(o -> log.debug("Arugument Value: " + o.toString()));
//		log.debug("Return Type:"+returnValue.toString());
	}
	
//	@AfterThrowing("springPackagePointCut() && springBeanPointCut()")
//	public void logAfterThrowing(JoinPoint joinpoint,Throwable ex) {
//		Throwable cause=ex.getCause();
//		if(cause!=null) {
//			log.error("Error Occured!",cause.getMessage());
//		}
//		else {
//			log.error("Error Occured!",ex.getMessage());
//		}
//	}
}
