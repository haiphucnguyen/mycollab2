package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;

public interface RiskMapperExt {
	void insertAndReturnKey(Risk risk);

	int getTotalCount(RiskSearchCriteria criteria);

	List<Risk> findPagableList(RiskSearchCriteria criteria, RowBounds rowBounds);
}
