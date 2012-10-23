package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleTarget;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetSearchCriteria;

public interface TargetMapperExt {
	public List findPagableList(TargetSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(TargetSearchCriteria criteria);

	SimpleTarget findTargetById(int targetId);
}
