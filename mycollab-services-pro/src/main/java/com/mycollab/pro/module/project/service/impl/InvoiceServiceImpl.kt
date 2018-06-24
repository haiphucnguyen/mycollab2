package com.mycollab.pro.module.project.service.impl

import com.mycollab.aspect.ClassInfo
import com.mycollab.aspect.ClassInfoMap
import com.mycollab.aspect.Traceable
import com.mycollab.common.ModuleNameConstants
import com.mycollab.core.cache.CacheKey
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.Invoice
import com.mycollab.module.project.domain.SimpleInvoice
import com.mycollab.module.project.domain.criteria.InvoiceSearchCriteria
import com.mycollab.module.project.service.InvoiceService
import com.mycollab.pro.module.project.dao.InvoiceMapper
import com.mycollab.pro.module.project.dao.InvoiceMapperExt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@Service
@Traceable(nameField = "noid", extraFieldName = "projectid")
@Transactional
open class InvoiceServiceImpl(private val invoiceMapper: InvoiceMapper,
                         private val invoiceMapperExt: InvoiceMapperExt) : DefaultService<Int, Invoice, InvoiceSearchCriteria>(), InvoiceService {

    override val searchMapper: ISearchableDAO<InvoiceSearchCriteria>
        get() = invoiceMapperExt

    override val crudMapper: ICrudGenericDAO<Int, Invoice>
        get() = invoiceMapper as ICrudGenericDAO<Int, Invoice>

    override fun findById(invoiceId: Int, @CacheKey sAccountId: Int): SimpleInvoice? =
            invoiceMapperExt.findInvoiceById(invoiceId)

    companion object {

        init {
            ClassInfoMap.put(InvoiceServiceImpl::class.java, ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.INVOICE))
        }
    }
}
