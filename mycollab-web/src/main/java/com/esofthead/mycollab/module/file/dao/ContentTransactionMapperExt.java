package com.esofthead.mycollab.module.file.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.file.domain.SimpleContentTransaction;
import com.esofthead.mycollab.module.file.domain.criteria.ContentTransactionSearchCriteria;

public interface ContentTransactionMapperExt {
	int getTotalCount(ContentTransactionSearchCriteria criteria);

	List<SimpleContentTransaction> findPagableList(
			ContentTransactionSearchCriteria criteria, RowBounds rowBounds);
}
