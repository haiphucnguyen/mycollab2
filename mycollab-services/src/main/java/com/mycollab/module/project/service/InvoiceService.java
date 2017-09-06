package com.mycollab.module.project.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public interface InvoiceService extends IDefaultService<Integer, Invoice, InvoiceSearchCriteria> {
    @Cacheable
    SimpleInvoice findById(Integer invoiceId, @CacheKey Integer sAccountId);

}
