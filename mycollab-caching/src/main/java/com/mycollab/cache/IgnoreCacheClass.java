package com.mycollab.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface IgnoreCacheClass {

}
