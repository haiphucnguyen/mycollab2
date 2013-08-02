package com.esofthead.mycollab.core.utils;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BeanUtility {

	public static String printBeanObj(Object bean) {
		return ToStringBuilder.reflectionToString(bean);
	}
}
