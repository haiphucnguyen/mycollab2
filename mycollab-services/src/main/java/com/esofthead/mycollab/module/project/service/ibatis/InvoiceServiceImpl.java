package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.InvoiceMapper;
import com.esofthead.mycollab.module.project.dao.InvoiceMapperExt;
import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.esofthead.mycollab.module.project.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@Service
@Transactional
@Traceable(nameField = "note", extraFieldName = "projectid")
@Watchable(userFieldName = "assignuser", extraTypeId = "projectid")
public class InvoiceServiceImpl extends DefaultService<Integer, Invoice, InvoiceSearchCriteria> implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceMapperExt invoiceMapperExt;

    @Override
    public ISearchableDAO<InvoiceSearchCriteria> getSearchMapper() {
        return invoiceMapperExt;
    }

    @Override
    public ICrudGenericDAO<Integer, Invoice> getCrudMapper() {
        return invoiceMapper;
    }

    @Override
    public SimpleInvoice findById(Integer invoiceId, @CacheKey Integer sAccountId) {
        return invoiceMapperExt.findInvoiceById(invoiceId);
    }
}
