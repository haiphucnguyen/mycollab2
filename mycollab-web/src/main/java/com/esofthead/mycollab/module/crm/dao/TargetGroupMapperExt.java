package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;

public interface TargetGroupMapperExt {
	public List findPagableList(TargetGroupSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(TargetGroupSearchCriteria criteria);
}
