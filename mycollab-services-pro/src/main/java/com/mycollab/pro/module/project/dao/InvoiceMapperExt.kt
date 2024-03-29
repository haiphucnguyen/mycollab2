package com.mycollab.pro.module.project.dao

import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.module.project.domain.SimpleInvoice
import com.mycollab.module.project.domain.criteria.InvoiceSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
interface InvoiceMapperExt : ISearchableDAO<InvoiceSearchCriteria> {
    fun findInvoiceById(invoiceId: Int?): SimpleInvoice?
}
