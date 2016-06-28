package com.esofthead.mycollab.pro.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public interface InvoiceMapperExt extends ISearchableDAO<InvoiceSearchCriteria> {
    SimpleInvoice findInvoiceById(Integer invoiceId);
}
