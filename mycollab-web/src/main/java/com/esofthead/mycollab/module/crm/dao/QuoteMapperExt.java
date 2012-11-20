package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;

public interface QuoteMapperExt extends ISearchableDAO<QuoteSearchCriteria> {

	int insertQuoteAndReturnKey(Quote quote);
}
