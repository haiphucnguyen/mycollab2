package com.mycollab.pro.module.project.service.impl;

import com.mycollab.aspect.ClassInfo;
import com.mycollab.aspect.ClassInfoMap;
import com.mycollab.aspect.Traceable;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.mycollab.module.project.service.InvoiceService;
import com.mycollab.pro.module.project.dao.InvoiceMapper;
import com.mycollab.pro.module.project.dao.InvoiceMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@Service
@Traceable(nameField = "noid", extraFieldName = "projectid")
@Transactional
public class InvoiceServiceImpl extends DefaultService<Integer, Invoice, InvoiceSearchCriteria> implements InvoiceService {

    static {
        ClassInfoMap.INSTANCE.put(InvoiceServiceImpl.class, new ClassInfo(ModuleNameConstants.INSTANCE.getPRJ(), ProjectTypeConstants.INSTANCE.getINVOICE()));
    }

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
