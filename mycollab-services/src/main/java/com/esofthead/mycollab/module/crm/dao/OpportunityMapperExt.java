package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityMapperExt extends
		ISearchableDAO<OpportunitySearchCriteria>,
		IMassUpdateDAO<Opportunity, OpportunitySearchCriteria>{

	SimpleOpportunity findById(int opportunityId);

	List<GroupItem> getSalesStageSummary(
			@Param("searchCriteria") OpportunitySearchCriteria criteria);

	List<GroupItem> getLeadSourcesSummary(
			@Param("searchCriteria") OpportunitySearchCriteria criteria);

}
