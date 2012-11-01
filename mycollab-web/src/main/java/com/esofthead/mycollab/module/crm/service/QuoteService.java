package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.SimpleQuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;

public interface QuoteService extends
		IDefaultService<Integer, Quote, QuoteSearchCriteria> {

	int insertQuoteAndReturnKey(Quote quote);

	void saveSimpleQuoteGroupProducts(int accountid, int quoteId,
			List<SimpleQuoteGroupProduct> entity);

	List<SimpleQuoteGroupProduct> getListSimpleQuoteGroupProducts(int quoteId);
}
