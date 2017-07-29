package com.mycollab.core.cache;

import java.lang.annotation.*;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface CacheArgs {
    Class<?>[] values();
}
