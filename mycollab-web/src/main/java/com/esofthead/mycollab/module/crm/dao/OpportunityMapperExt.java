package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OpportunityMapperExt extends
        ISearchableDAO<OpportunitySearchCriteria> {

    SimpleOpportunity findOpportunityById(int opportunityId);

    List<GroupItem> getSalesStageSummary(OpportunitySearchCriteria criteria);
    
    List<GroupItem> getLeadSourcesSummary(OpportunitySearchCriteria criteria);
    
    public abstract void upateOpportunityBySearchCriteria(@Param("record") Opportunity record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
