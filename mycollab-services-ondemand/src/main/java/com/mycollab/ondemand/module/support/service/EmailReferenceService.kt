package com.mycollab.ondemand.module.support.service

import com.mycollab.db.persistence.service.IService

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
interface EmailReferenceService : IService {
    fun save(email: String)

    fun remove(email: String)
}
