package com.esofthead.mycollab.common.interceptor.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchRequest;

@Aspect
@Component
@Configurable
public class CacheAspect {
	private Logger log = LoggerFactory.getLogger(CacheAspect.class);

	@Around("execution(public * com.esofthead.mycollab..service..*.findPagableListByCriteria(..)) && args(searchRequest)")
	public Object cacheFindList(ProceedingJoinPoint pjp,
			SearchRequest searchRequest) {
		Advised advised = (Advised) pjp.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		try {
			Object returnVal = pjp.proceed();
			return returnVal;
		} catch (Throwable e) {
			if (e instanceof MyCollabException) {
				throw (MyCollabException) e;
			} else {
				throw new MyCollabException(e);
			}
		}
	}
}
