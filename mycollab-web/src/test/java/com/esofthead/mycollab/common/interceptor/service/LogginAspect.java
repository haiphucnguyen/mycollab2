package com.esofthead.mycollab.common.interceptor.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LogginAspect {
	@Before("execution(* com.esofthead.mycollab.**.*(..))")
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
}
