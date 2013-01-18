package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import java.util.List;

public interface OpportunityMapperExt extends
        ISearchableDAO<OpportunitySearchCriteria> {

    SimpleOpportunity findOpportunityById(int opportunityId);

    List<GroupItem> getSalesStageSummary(OpportunitySearchCriteria criteria);
}
