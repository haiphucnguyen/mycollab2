package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityMapperExt {
	public List findPagableList(OpportunitySearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(OpportunitySearchCriteria criteria);

	SimpleOpportunity findOpportunityById(int opportunityId);
}
