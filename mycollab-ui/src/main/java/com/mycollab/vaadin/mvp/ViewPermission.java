package com.mycollab.vaadin.mvp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface ViewPermission {
    String permissionId();

    int impliedPermissionVal();
}
