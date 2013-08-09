/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.interceptor.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.GregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheManager;
import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.dao.AuditLogMapper;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.common.service.ibatis.AuditLogServiceImpl.AuditLogUtil;
import com.esofthead.mycollab.core.utils.BeanUtility;

/**
 * 
 * @author haiphucnguyen
 */
@Aspect
@Component
@Configurable
public class AuditLogAspect {

	private static Logger log = LoggerFactory.getLogger(AuditLogAspect.class);
	private static BasicCache<Object, Object> caches = CacheManager.getCache();

	@Autowired
	protected AuditLogMapper auditLogMapper;

	@Autowired
	private ActivityStreamService activityStreamService;

	@Autowired
	protected MonitorItemService monitorItemService;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Before("execution(public * com.esofthead.mycollab..service..*.updateWithSession(..)) && args(bean, username)")
	public void traceBeforeUpdateActivity(JoinPoint joinPoint, Object bean,
			String username) {

		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		if (auditAnnotation != null) {
			try {
				int typeid = (Integer) PropertyUtils.getProperty(bean, "id");
				// store old value to map, wait until the update process
				// successfully then add to log item

				// get old value
				Object service = advised.getTargetSource().getTarget();
				Method findMethod = null;
				Object oldValue = null;
				try {
					findMethod = cls.getMethod("findById",
							new Class[] { Integer.class });
				} catch (Exception e) {
					findMethod = cls.getMethod("findByPrimaryKey",
							new Class[] { Serializable.class });
				}
				oldValue = findMethod.invoke(service, typeid);
				String key = bean.toString() + auditAnnotation.type() + typeid;

				caches.put(key, oldValue);
			} catch (Exception e) {
				log.error("Error when save audit for save action of service "
						+ cls.getName(), e);
			}
		}
	}

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.updateWithSession(..)) && args(bean, username)")
	public void traceAfterUpdateActivity(JoinPoint joinPoint, Object bean,
			String username) {

		Advised advised = (Advised) joinPoint.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		Traceable traceableAnnotation = cls.getAnnotation(Traceable.class);
		Integer activityStreamId = null;
		if (traceableAnnotation != null) {
			try {
				ActivityStream activity = TraceableAspect.constructActivity(
						traceableAnnotation, bean, username,
						ActivityStreamConstants.ACTION_UPDATE);
				activityStreamId = activityStreamService.save(activity);
			} catch (Exception e) {
				log.error(
						"Error when save activity for save action of service "
								+ cls.getName(), e);
			}
		}

		Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		if (auditAnnotation != null) {
			String key = null;
			try {

				int typeid = (Integer) PropertyUtils.getProperty(bean, "id");
				key = bean.toString() + auditAnnotation.type() + typeid;
				int sAccountId = (Integer) PropertyUtils.getProperty(bean,
						"saccountid");
				Object oldValue = caches.get(key);
				if (oldValue != null) {
					AuditLog auditLog = new AuditLog();
					auditLog.setPosteduser(username);
					auditLog.setModule(auditAnnotation.module());
					auditLog.setType(auditAnnotation.type());
					auditLog.setTypeid(typeid);
					auditLog.setSaccountid(sAccountId);
					auditLog.setPosteddate(new GregorianCalendar().getTime());
					auditLog.setChangeset(AuditLogUtil.getChangeSet(oldValue,
							bean));
					auditLog.setObjectClass(oldValue.getClass().getName());
					if (activityStreamId != null) {
						auditLog.setActivitylogid(activityStreamId);
					}
					int auditLogId = auditLogMapper
							.insertAndReturnKey(auditLog);

					caches.remove(key);

					// Add watchable item to relay email notify associate with
					// change
					Watchable watchableAnnotation = cls
							.getAnnotation(Watchable.class);
					if (watchableAnnotation != null) {
						int monitorTypeId = (Integer) PropertyUtils
								.getProperty(bean, "id");
						String monitorType = watchableAnnotation.type();

						String moreUser = (String) PropertyUtils.getProperty(
								bean, watchableAnnotation.userFieldName());
						// check whether the current user is in monitor list, if
						// not add him in
						if (moreUser != null && !moreUser.equals(username)) {
							if (!monitorItemService.isUserWatchingItem(
									moreUser, monitorType, monitorTypeId)) {
								MonitorItem monitorItem = new MonitorItem();
								monitorItem
										.setMonitorDate(new GregorianCalendar()
												.getTime());
								monitorItem.setType(monitorType);
								monitorItem.setTypeid(monitorTypeId);
								monitorItem.setUser(moreUser);
								monitorItemService.saveWithSession(monitorItem,
										moreUser);
							}
						}

						// Save notification email
						log.debug("AUDIT LOG ID: " + auditLogId);
						RelayEmailNotification relayNotification = new RelayEmailNotification();
						relayNotification.setChangeby(username);
						relayNotification.setChangecomment("");
						relayNotification.setSaccountid(sAccountId);
						relayNotification.setType(monitorType);
						relayNotification.setTypeid(monitorTypeId);
						relayNotification
								.setEmailhandlerbean(watchableAnnotation
										.emailHandlerBean().getName());
						relayNotification.setExtratypeid(auditLogId);
						relayNotification
								.setAction(MonitorTypeConstants.UPDATE_ACTION);

						relayEmailNotificationService.saveWithSession(
								relayNotification, username);
					}
				}
			} catch (Exception e) {
				log.error(
						"Error when save audit for save action of service "
								+ cls.getName() + "and bean: "
								+ BeanUtility.printBeanObj(bean), e);
			} finally {
				if (key != null) {
					caches.remove(key);
				}
			}
		}
	}
}
