package com.esofthead.mycollab.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		ctx = appContext;

	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static <T> T getBean(Class<T> classType) {
		return ctx.getBean(classType);
	}
}
