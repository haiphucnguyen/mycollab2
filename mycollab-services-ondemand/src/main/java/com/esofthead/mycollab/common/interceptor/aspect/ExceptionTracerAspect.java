package com.esofthead.mycollab.common.interceptor.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.utils.BeanUtility;

@Aspect
@Component
public class ExceptionTracerAspect {
	private static Logger LOG = LoggerFactory
			.getLogger(ExceptionTracerAspect.class);

	@AfterThrowing(pointcut = "execution(public * com.esofthead.mycollab..service..*.*(..))", throwing = "e")
	public void traceError(JoinPoint joinPoint, Exception e) {
		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		StringBuffer errorMsg = new StringBuffer("Class name: ").append(cls
				.getName());
		errorMsg.append(" Method: ").append(methodName).append(". Arguments: ");
		for (Object arg : args) {
			errorMsg.append(BeanUtility.printBeanObj(arg));
		}
		LOG.error(errorMsg.toString());
	}
}
