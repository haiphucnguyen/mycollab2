/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.interceptor.service;

import com.esofthead.mycollab.common.service.AuditLogService;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 *
 * @author haiphucnguyen
 */
@Aspect
@Component
@Configurable
public class AuditLogAspect {

    private static Logger log = LoggerFactory.getLogger(AuditLogAspect.class);
    private static Map<String, Object> caches = new HashMap<String, Object>();
    @Autowired
    protected AuditLogService auditLogService;

    @Before("execution(public * com.esofthead.mycollab..service..*.updateWithSession(..)) && args(bean, username)")
    public void traceBeforeUpdateActivity(JoinPoint joinPoint, Object bean,
            String username) {

        Advised advised = (Advised) joinPoint.getThis();
        Class<?> cls = advised.getTargetSource().getTargetClass();


        Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
        if (auditAnnotation != null) {
            try {
                int typeid = (Integer) PropertyUtils.getProperty(bean, "id");
                //store old value to map, wait until the update process successfully then add to log item

                //get old value
                Object service = advised.getTargetSource().getTarget();
                Method findMethod = cls.getMethod("findByPrimaryKey", new Class[]{Serializable.class});
                Object oldValue = findMethod.invoke(service, typeid);
                String key = bean.toString() + auditAnnotation.type() + typeid;

                caches.put(key, oldValue);
            } catch (Exception e) {
                log.error(
                        "Error when save audit for save action of service "
                        + cls.getName(), e);
            }
        }
    }

    @After("execution(public * com.esofthead.mycollab..service..*.updateWithSession(..)) && args(bean, username)")
    public void traceAfterUpdateActivity(JoinPoint joinPoint, Object bean,
            String username) {

        Advised advised = (Advised) joinPoint.getThis();
        Class<?> cls = advised.getTargetSource().getTargetClass();

        Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
        if (auditAnnotation != null) {
            String key = null;
            try {

                int typeid = (Integer) PropertyUtils.getProperty(bean, "id");
                key = bean.toString() + auditAnnotation.type() + typeid;
                int sAccountId = (Integer) PropertyUtils.getProperty(bean, "saccountid");
                Object oldValue = caches.get(key);
                if (oldValue != null) {
                    log.debug("Save audit log for service " + bean);
                    auditLogService.saveAuditLog(username, auditAnnotation.module(), auditAnnotation.type(), typeid, sAccountId, oldValue, bean);
                    caches.remove(key);
                }
            } catch (Exception e) {
                log.error(
                        "Error when save audit for save action of service "
                        + cls.getName(), e);
                if (key != null) {
                    caches.remove(key);
                }
            }
        }
    }
}
