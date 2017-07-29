package com.mycollab.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE })
public @interface Watchable {
	String userFieldName() default "";
	
	String extraTypeId() default "";
}