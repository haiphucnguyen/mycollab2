package com.esofthead.mycollab.common.interceptor.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
public @interface Traceable {
	String module();

	String type();

	String idField() default "id";

//	String nameField();
}
