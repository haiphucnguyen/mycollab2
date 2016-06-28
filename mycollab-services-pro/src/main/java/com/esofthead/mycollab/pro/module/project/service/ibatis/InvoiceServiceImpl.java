package com.esofthead.mycollab.pro.module.project.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfo;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfoMap;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.esofthead.mycollab.module.project.service.InvoiceService;
import com.esofthead.mycollab.pro.module.project.dao.InvoiceMapper;
import com.esofthead.mycollab.pro.module.project.dao.InvoiceMapperExt;
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
        ClassInfoMap.put(InvoiceServiceImpl.class, new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.INVOICE));
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
