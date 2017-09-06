package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.crm.dao.QuoteGroupProductMapper;
import com.mycollab.module.crm.dao.QuoteGroupProductMapperExt;
import com.mycollab.module.crm.domain.QuoteGroupProduct;
import com.mycollab.module.crm.domain.QuoteGroupProductExample;
import com.mycollab.module.crm.service.QuoteGroupProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuoteGroupServiceImpl extends DefaultCrudService<Integer, QuoteGroupProduct> implements QuoteGroupProductService {

    @Autowired
    private QuoteGroupProductMapper quoteGroupProductMapper;

    @Autowired
    private QuoteGroupProductMapperExt quoteGroupProductMapperExt;

    @Override
    public ICrudGenericDAO<Integer, QuoteGroupProduct> getCrudMapper() {
        return quoteGroupProductMapper;
    }

    @Override
    public List<QuoteGroupProduct> findQuoteGroupByQuoteId(int quoteid) {
        QuoteGroupProductExample ex = new QuoteGroupProductExample();
        ex.createCriteria().andQuoteidEqualTo(quoteid);
        return quoteGroupProductMapper.selectByExample(ex);
    }

    @Override
    public void deleteQuoteGroupByQuoteId(int quoteid) {
        QuoteGroupProductExample ex = new QuoteGroupProductExample();
        ex.createCriteria().andQuoteidEqualTo(quoteid);
        quoteGroupProductMapper.deleteByExample(ex);
    }

    @Override
    public int insertQuoteGroupExt(QuoteGroupProduct record) {
        quoteGroupProductMapperExt.insertQuoteGroupExt(record);
        return record.getId();
    }

}
