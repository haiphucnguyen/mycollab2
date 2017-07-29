package com.mycollab.module.crm.service;

import com.mycollab.module.crm.domain.QuoteGroupProduct;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface QuoteGroupProductService {
    List<QuoteGroupProduct> findQuoteGroupByQuoteId(int quoteid);

    void deleteQuoteGroupByQuoteId(int quoteid);

    int insertQuoteGroupExt(QuoteGroupProduct record);
}
