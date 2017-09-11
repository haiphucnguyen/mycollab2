package com.mycollab.aspect

import java.util.HashMap

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
object ClassInfoMap {
    private val mapWrapper = HashMap<Class<*>, ClassInfo>()

    fun put(cls: Class<*>, classInfo: ClassInfo) {
        mapWrapper.put(cls, classInfo)
    }

    fun getClassInfo(cls: Class<*>): ClassInfo? {
        return mapWrapper[cls]
    }

    fun getModule(cls: Class<*>): String {
        return mapWrapper[cls]!!.module!!
    }

    fun getType(cls: Class<*>): String {
        return mapWrapper[cls]!!.type!!
    }
}
