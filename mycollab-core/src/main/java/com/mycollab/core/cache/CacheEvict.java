package com.mycollab.core.cache;

import java.lang.annotation.*;

/**
 * <b>NOTE: </b> Implement of cache just be presented in premium or ondemand
 * delivery.<br/>
 * This annotation denotes a method has data clean of cache with the key compose
 * by its arguments.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface CacheEvict {
}
