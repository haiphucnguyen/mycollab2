package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseMapperExt {
	
	List findPagableList(CaseSearchCriteria criteria,
			RowBounds rowBounds);
	
	int getTotalCount(CaseSearchCriteria criteria);
}
