package com.mycollab.module.project.service

/**
 * @author MyCollab Ltd
 * @since 7.0.2
 */
interface TicketKeyService {
    fun getMaxKey(projectId: Int): Int?

    fun getNextKey(projectId: Int, currentKey:Int): Int?

    fun getPreviousKey(projectId: Int, currentKey: Int): Int?

    fun saveKey(projectId: Int, ticketId:Int, ticketType:String, ticketKey: Int)
}