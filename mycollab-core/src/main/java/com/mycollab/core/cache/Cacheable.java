package com.mycollab.core.cache;

import java.lang.annotation.*;

/**
 * <b>NOTE: </b> Implement of cache just be presented in premium or ondemand
 * delivery.<br/>
 * This annotation denotes a method is cached its value base on the key compose
 * by its arguments. Mycollab cache user data base on user account value base on
 * user account
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface Cacheable {

}
