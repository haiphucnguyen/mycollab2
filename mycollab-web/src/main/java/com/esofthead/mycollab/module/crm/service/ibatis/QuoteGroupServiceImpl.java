package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.QuoteGroupProductMapper;
import com.esofthead.mycollab.module.crm.dao.QuoteGroupProductMapperExt;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProductExample;
import com.esofthead.mycollab.module.crm.service.QuoteGroupProductService;

@Service
public class QuoteGroupServiceImpl extends
		DefaultCrudService<Integer, QuoteGroupProduct> implements
		QuoteGroupProductService {
	
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
