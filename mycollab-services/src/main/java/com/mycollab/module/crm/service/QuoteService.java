package com.mycollab.module.crm.service;

import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.Quote;
import com.mycollab.module.crm.domain.SimpleQuoteGroupProduct;
import com.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface QuoteService extends IDefaultService<Integer, Quote, QuoteSearchCriteria> {

    void saveSimpleQuoteGroupProducts(int accountid, int quoteId, List<SimpleQuoteGroupProduct> entity);

    List<SimpleQuoteGroupProduct> getListSimpleQuoteGroupProducts(int quoteId);
}
