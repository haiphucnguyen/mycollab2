package com.esofthead.mycollab.common.interceptor.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE })
public @interface Watchable {
	String type();
	
	String userFieldName() default "";
	
	Class emailHandlerBean() default String.class;
}
