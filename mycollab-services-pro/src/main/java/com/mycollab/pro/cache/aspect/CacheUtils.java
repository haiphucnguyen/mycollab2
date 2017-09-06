package com.mycollab.pro.cache.aspect;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.core.utils.ClassUtils;
import com.mycollab.db.persistence.service.IService;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class CacheUtils {

    static Class<?> getEnclosingServiceInterface(Class<?> serviceClass) {
        return ClassUtils.getInterfaceInstanceOf(serviceClass, IService.class);
    }

    static String getEnclosingServiceInterfaceName(Class<?> serviceClass) {
        return getEnclosingServiceInterface(serviceClass).getName();
    }

    static boolean isInBlackList(Class<?> cls) {
        return (cls != null) && (cls.getAnnotation(IgnoreCacheClass.class) != null);
    }
}
