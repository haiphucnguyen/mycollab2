package com.mycollab.ondemand.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.mycollab.core.UserInvalidInputException;
import com.mycollab.module.billing.UsageExceedBillingPlanException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.stereotype.Component;

import com.mycollab.core.utils.BeanUtility;

@Aspect
@Component
public class ExceptionTracerAspect {
	private static Logger LOG = LoggerFactory.getLogger(ExceptionTracerAspect.class);

	@AfterThrowing(pointcut = "execution(public * com.mycollab..service..*.*(..))", throwing = "e")
	public void traceError(JoinPoint joinPoint, Exception e) {
		if (e instanceof UserInvalidInputException || e instanceof UsageExceedBillingPlanException) {
			return;
		}
		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		StringBuffer errorMsg = new StringBuffer("Class name: ").append(cls.getName());
		errorMsg.append(" Method: ").append(methodName).append(". Arguments: ");
		for (Object arg : args) {
			errorMsg.append(BeanUtility.printBeanObj(arg));
		}

		StringWriter writer = new StringWriter();
		PrintWriter printW = new PrintWriter(writer);
		e.printStackTrace(printW);

		errorMsg.append(". Exception: ");
		errorMsg.append(writer.toString());

		LOG.error(errorMsg.toString());
	}
}
