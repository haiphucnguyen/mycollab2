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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.web.AppContext;

@Aspect
@Component
@Configurable
public class MonitorItemAspect {
	private static Logger log = LoggerFactory
			.getLogger(MonitorItemAspect.class);

	@Autowired
	private MonitorItemService monitorItemService;

	@After("execution(public * com.esofthead.mycollab..service..*.saveWithSession(..)) && args(bean, username)")
	public void traceSaveActivity(JoinPoint joinPoint, Object bean,
			String username) {

		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		Watchable watchableAnnotation = cls.getAnnotation(Watchable.class);
		if (watchableAnnotation != null) {
			try {
				MonitorItem monitorItem = new MonitorItem();
				monitorItem.setMonitorDate(new GregorianCalendar().getTime());
				monitorItem.setType(watchableAnnotation.type());
				monitorItem.setTypeid((Integer) PropertyUtils.getProperty(bean,
						"id"));
				monitorItem.setUser(username);

				MonitorItemService monitorItemService = AppContext
						.getSpringBean(MonitorItemService.class);
				monitorItemService.saveWithSession(monitorItem, username);
			} catch (Exception e) {
				log.error(
						"Error when save activity for save action of service "
								+ cls.getName(), e);
			}
		}

	}
}
