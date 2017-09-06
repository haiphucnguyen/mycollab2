package com.mycollab.core.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyCollab cache is a distributed map with key and value. This annotation
 * denote a pameter of method is played as cache key.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface CacheKey {

}
