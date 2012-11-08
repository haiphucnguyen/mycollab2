package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.ChangeLog;
import com.esofthead.mycollab.module.project.domain.criteria.ChangeLogSearchCriteria;

public interface ChangeLogMapperExt {
	int getTotalCount(ChangeLogSearchCriteria criteria);

	List<ChangeLog> findPagableList(ChangeLogSearchCriteria criteria,
			RowBounds rowBounds);
}
