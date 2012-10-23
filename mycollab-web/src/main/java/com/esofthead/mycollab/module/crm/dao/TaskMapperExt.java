package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;

public interface TaskMapperExt {
	List findPagableList(TaskSearchCriteria criteria,
			RowBounds rowBounds);

	int getTotalCount(TaskSearchCriteria criteria);
}
