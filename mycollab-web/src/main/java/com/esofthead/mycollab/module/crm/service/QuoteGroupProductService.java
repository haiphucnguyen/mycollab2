package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.QuoteGroupProduct;

public interface QuoteGroupProductService {
	List<QuoteGroupProduct> findQuoteGroupByQuoteId(int quoteid);
	
	void deleteQuoteGroupByQuoteId(int quoteid);
	
	int insertQuoteGroupExt(QuoteGroupProduct record);
}
