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

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.utils.BeanUtility;

@Aspect
@Component
@Configurable
public class MonitorItemAspect {
	private static Logger log = LoggerFactory
			.getLogger(MonitorItemAspect.class);

	@Autowired
	private MonitorItemService monitorItemService;
	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

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

				monitorItemService.saveWithSession(monitorItem, username);
				log.debug("Save monitor item: "
						+ BeanUtility.printBeanObj(monitorItem));

				if (!watchableAnnotation.userFieldName().equals("")) {
					String moreUser = (String) PropertyUtils.getProperty(bean,
							watchableAnnotation.userFieldName());
					if (moreUser != null && !moreUser.equals(username)) {
						monitorItem.setId(null);
						monitorItem.setUser(moreUser);
						monitorItemService.saveWithSession(monitorItem,
								moreUser);
					}
				}

				RelayEmailNotification relayNotification = new RelayEmailNotification();
				relayNotification.setChangeby(username);
				relayNotification.setChangecomment("");
				int sAccountId = (Integer) PropertyUtils.getProperty(bean,
						"saccountid");
				relayNotification.setSaccountid(sAccountId);
				relayNotification.setType(watchableAnnotation.type());
				relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
				int typeid = (Integer) PropertyUtils.getProperty(bean, "id");
				relayNotification.setTypeid(typeid);
				relayNotification.setEmailhandlerbean(watchableAnnotation
						.emailHandlerBean().getName());
				relayEmailNotificationService.saveWithSession(
						relayNotification, username);
				// Save notification item

			} catch (Exception e) {
				log.error(
						"Error when save relay email notification for save action of service "
								+ cls.getName(), e);
			}
		}

	}
}
