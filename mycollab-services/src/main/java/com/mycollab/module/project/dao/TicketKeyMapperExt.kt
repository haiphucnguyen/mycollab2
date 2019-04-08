package com.mycollab.module.project.dao

/**
 * @author MyCollab
 * @since 7.0.2
 */
interface TicketKeyMapperExt {
    fun getMaxKey(projectId: Int): Int?

    fun getNextKey(projectId: Int, currentKey:Int): Int?

    fun getPreviousKey(projectId: Int, currentKey: Int): Int?
}