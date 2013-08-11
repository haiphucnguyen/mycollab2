package com.esofthead.mycollab.core.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BeanUtility {

	public static String printBeanObj(Object bean) {
		return ToStringBuilder.reflectionToString(bean,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
