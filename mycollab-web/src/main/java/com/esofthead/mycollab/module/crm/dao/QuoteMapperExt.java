package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.SimpleQuote;
import com.esofthead.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;

public interface QuoteMapperExt {
	int getTotalCount(QuoteSearchCriteria criteria);

	List<SimpleQuote> findPagableList(QuoteSearchCriteria criteria,
			RowBounds rowBounds);

	int insertQuoteAndReturnKey(Quote quote);
}
