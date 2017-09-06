package com.mycollab.module.crm.service;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.core.cache.CacheArgs;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.OpportunityLead;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface OpportunityService extends IDefaultService<Integer, Opportunity, OpportunitySearchCriteria> {

    @Cacheable
    SimpleOpportunity findById(Integer opportunityId, @CacheKey Integer sAccountId);

    @Cacheable
    List<GroupItem> getSalesStageSummary(@CacheKey OpportunitySearchCriteria criteria);

    @Cacheable
    List<GroupItem> getPipeline(@CacheKey OpportunitySearchCriteria criteria);

    @Cacheable
    List<GroupItem> getLeadSourcesSummary(@CacheKey OpportunitySearchCriteria criteria);

    @CacheEvict
    @CacheArgs(values = {LeadService.class})
    void saveOpportunityLeadRelationship(List<OpportunityLead> associateLeads, @CacheKey Integer sAccountId);

    @CacheEvict
    @CacheArgs(values = {LeadService.class})
    void removeOpportunityLeadRelationship(OpportunityLead associateLead, @CacheKey Integer sAccountId);

    @Cacheable
    SimpleOpportunity findOpportunityAssoWithConvertedLead(Integer leadId, @CacheKey Integer accountId);
}
