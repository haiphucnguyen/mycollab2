package com.esofthead.mycollab.common.interceptor.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

@Aspect
@Component
public class TraceableAspect {
	@Autowired
	private ActivityStreamService activityStreamService;

	@After("execution(public * com.esofthead.mycollab..service..*.saveWithSession(..)) && args(bean, username)")
	public void logBefore(JoinPoint joinPoint, Object bean, String username) {

		@SuppressWarnings("rawtypes")
		ICrudService service = (ICrudService) joinPoint.getThis();

		Traceable traceableAnnotation = service.getClass().getAnnotation(
				Traceable.class);
		if (traceableAnnotation != null) {
			System.out.println("Annotation:");
		}
		
		System.out.println("hijacked : "
				+ joinPoint.getSignature().getDeclaringType() + " "
				+ joinPoint.getThis() + "  " + joinPoint.getTarget() + " "
				+ bean + " " + username + " " + activityStreamService);
	}
}
