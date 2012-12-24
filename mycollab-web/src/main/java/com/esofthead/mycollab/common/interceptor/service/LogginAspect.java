package com.esofthead.mycollab.common.interceptor.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Aspect
@Configurable
@Component
public class LogginAspect {
	@Before("execution(public * com.esofthead.mycollab..service..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("hijacked : "
				+ joinPoint.getSignature().toLongString() + " "
				+ joinPoint.getThis() + "  " + joinPoint.getTarget());
	}
}
