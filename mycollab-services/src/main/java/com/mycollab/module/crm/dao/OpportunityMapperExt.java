package com.mycollab.module.crm.dao;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.db.persistence.IMassUpdateDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface OpportunityMapperExt extends ISearchableDAO<OpportunitySearchCriteria>, IMassUpdateDAO<Opportunity, OpportunitySearchCriteria> {

    SimpleOpportunity findById(Integer opportunityId);

    List<GroupItem> getSalesStageSummary(@Param("searchCriteria") OpportunitySearchCriteria criteria);

    List<GroupItem> getLeadSourcesSummary(@Param("searchCriteria") OpportunitySearchCriteria criteria);

    List<GroupItem> getPipeline(@Param("searchCriteria") OpportunitySearchCriteria criteria);

    SimpleOpportunity findOpportunityAssoWithConvertedLead(@Param("leadId") int leadId);
}
