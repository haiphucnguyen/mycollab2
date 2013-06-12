package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityMapperExt extends
		ISearchableDAO<OpportunitySearchCriteria> {

	SimpleOpportunity findOpportunityById(int opportunityId);

	List<GroupItem> getSalesStageSummary(
			@Param("searchCriteria") OpportunitySearchCriteria criteria);

	List<GroupItem> getLeadSourcesSummary(
			@Param("searchCriteria") OpportunitySearchCriteria criteria);

	public abstract void upateOpportunityBySearchCriteria(
			@Param("record") Opportunity record,
			@Param("searchCriteria") SearchCriteria searchCriteria);
}
