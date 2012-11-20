package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityMapperExt extends
		ISearchableDAO<OpportunitySearchCriteria> {

	SimpleOpportunity findOpportunityById(int opportunityId);
}
