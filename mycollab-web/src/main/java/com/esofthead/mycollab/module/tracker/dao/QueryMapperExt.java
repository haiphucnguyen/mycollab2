package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.criteria.QuerySearchCriteria;

public interface QueryMapperExt {
	int getTotalCount(QuerySearchCriteria criteria);

	List<Query> findPagableList(QuerySearchCriteria criteria,
			RowBounds rowBounds);
}
