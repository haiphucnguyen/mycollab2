package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.Lead;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface LeadService extends IDefaultService<Integer, Lead, LeadSearchCriteria> {
    @Cacheable
    SimpleLead findById(Integer leadId, @CacheKey Integer sAccountId);

    @CacheEvict
    void convertLead(SimpleLead lead, Opportunity opportunity, String convertUser);

    SimpleLead findConvertedLeadOfAccount(Integer accountId, @CacheKey Integer sAccountId);

    SimpleLead findConvertedLeadOfContact(Integer contactId, @CacheKey Integer sAccountId);

    SimpleLead findConvertedLeadOfOpportunity(Integer opportunity, @CacheKey Integer sAccountId);
}
