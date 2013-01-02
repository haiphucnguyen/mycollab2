/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.interceptor.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author haiphucnguyen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
public @interface Auditable {
    String type();
    
    String module();
}
