package com.mycollab.ondemand.module.support.service.impl

import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper
import com.mycollab.ondemand.module.support.domain.EmailReference
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
class EmailReferenceServiceImpl(private val emailReferenceMapper: EmailReferenceMapper) : EmailReferenceService {

    override fun save(email: String) {
        val ex = EmailReferenceExample()
        ex.createCriteria().andEmailEqualTo(email)
        if (emailReferenceMapper.countByExample(ex) == 0L) {
            val emailReference = EmailReference()
            emailReference.createdtime = LocalDateTime.now()
            emailReference.email = email
            emailReference.subscribe = java.lang.Boolean.TRUE
            emailReferenceMapper.insert(emailReference)
        }
    }

    override fun remove(email: String) {
        val ex = EmailReferenceExample()
        ex.createCriteria().andEmailEqualTo(email)
        emailReferenceMapper.deleteByExample(ex)
    }
}
