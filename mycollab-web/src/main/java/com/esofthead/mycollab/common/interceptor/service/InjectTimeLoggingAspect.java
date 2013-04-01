/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.interceptor.service;

import java.util.GregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author haiphucnguyen
 */
@Aspect
@Component
public class InjectTimeLoggingAspect {

    private static Logger log = LoggerFactory.getLogger(InjectTimeLoggingAspect.class);

    @Before("execution(public * com.esofthead.mycollab..service..*.saveWithSession(..)) && args(bean, username)")
    public void injectDateForSaveMethod(JoinPoint joinPoint, Object bean,
            String username) {

        try {
            log.debug("Set createtime and lastupdatedtime if enable");
            PropertyUtils.setProperty(bean, "createdtime",
                    new GregorianCalendar().getTime());
            PropertyUtils.setProperty(bean, "lastupdatedtime",
                    new GregorianCalendar().getTime());
        } catch (Exception e) {
        }
    }

    @Before("execution(public * com.esofthead.mycollab..service..*.updateWithSession(..)) && args(bean, username)")
    public void injectDateForUpdateMethod(JoinPoint joinPoint, Object bean,
            String username) {

        try {
            log.debug("Set createtime and lastupdatedtime if enable");
            PropertyUtils.setProperty(bean, "lastupdatedtime",
                    new GregorianCalendar().getTime());
        } catch (Exception e) {
        }
    }
}
