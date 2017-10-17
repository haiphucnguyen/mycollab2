package com.mycollab.pro.cache.aspect

import com.mycollab.cache.IgnoreCacheClass
import com.mycollab.core.utils.ClassUtils
import com.mycollab.db.persistence.service.IService

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
internal object CacheUtils {

    fun getEnclosingServiceInterface(serviceClass: Class<*>): Class<*>? =
            ClassUtils.getInterfaceInstanceOf(serviceClass, IService::class.java)

    fun getEnclosingServiceInterfaceName(serviceClass: Class<*>): String =
            getEnclosingServiceInterface(serviceClass)!!.name

    fun isInBlackList(cls: Class<*>?): Boolean = cls?.getAnnotation(IgnoreCacheClass::class.java) != null
}
