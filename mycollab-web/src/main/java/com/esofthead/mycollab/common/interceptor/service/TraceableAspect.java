package com.esofthead.mycollab.common.interceptor.service;

import java.util.GregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.service.ActivityStreamService;

@Aspect
@Component
public class TraceableAspect {
	private static Logger log = LoggerFactory.getLogger(TraceableAspect.class);

	@Autowired
	private ActivityStreamService activityStreamService;

	@After("execution(public * com.esofthead.mycollab..service..*.saveWithSession(..)) && args(bean, username)")
	public void traceSaveActivity(JoinPoint joinPoint, Object bean,
			String username) {

		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		Traceable traceableAnnotation = cls.getAnnotation(Traceable.class);
		if (traceableAnnotation != null) {
			try {
				ActivityStream activity = new ActivityStream();
				activity.setModule(traceableAnnotation.module());
				activity.setType(traceableAnnotation.type());
				activity.setTypeid((Integer) PropertyUtils.getProperty(bean,
						traceableAnnotation.idField()));
				activity.setCreatedtime(new GregorianCalendar().getTime());
				activity.setAction(ActivityStreamConstants.ACTION_CREATE);
				activity.setSaccountid((Integer) PropertyUtils.getProperty(
						bean, "saccountid"));
				activityStreamService.save(activity);
			} catch (Exception e) {
				log.error(
						"Error when save activity for save action of service "
								+ cls.getName(), e);
			}
		}

	}
}
