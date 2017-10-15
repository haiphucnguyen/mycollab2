package com.mycollab.ondemand.module.support.service.impl

import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper
import com.mycollab.ondemand.module.support.domain.EmailReference
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.GregorianCalendar

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
class EmailReferenceServiceImpl(private val emailReferenceMapper: EmailReferenceMapper) : EmailReferenceService {

    override fun save(email: String) {
        val ex = EmailReferenceExample()
        ex.createCriteria().andEmailEqualTo(email)
        if (emailReferenceMapper.countByExample(ex) == 0) {
            val emailReference = EmailReference()
            emailReference.createdtime = GregorianCalendar().time
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
