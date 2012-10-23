package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.QuoteGroupProductMapper;
import com.esofthead.mycollab.module.crm.dao.QuoteGroupProductMapperExt;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProductExample;
import com.esofthead.mycollab.module.crm.service.QuoteGroupProductService;

public class QuoteGroupServiceImpl extends
		DefaultCrudService<QuoteGroupProduct, Integer> implements
		QuoteGroupProductService {

	private QuoteGroupProductMapperExt daoExt;

	public void setDaoExt(QuoteGroupProductMapperExt daoExt) {
		this.daoExt = daoExt;
	}

	@Override
	public List<QuoteGroupProduct> findQuoteGroupByQuoteId(int quoteid) {
		QuoteGroupProductExample ex = new QuoteGroupProductExample();
		ex.createCriteria().andQuoteidEqualTo(quoteid);

		return ((QuoteGroupProductMapper) daoObj).selectByExample(ex);
	}

	@Override
	public void deleteQuoteGroupByQuoteId(int quoteid) {
		QuoteGroupProductExample ex = new QuoteGroupProductExample();
		ex.createCriteria().andQuoteidEqualTo(quoteid);

		((QuoteGroupProductMapper) daoObj).deleteByExample(ex);

	}

	@Override
	public int insertQuoteGroupExt(QuoteGroupProduct record) {
		daoExt.insertQuoteGroupExt(record);
		return record.getId();
	}

}
