package com.mycollab.vaadin.mvp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadPolicy {
    ViewScope scope() default ViewScope.SESSION;
}
