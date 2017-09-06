package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheArgs;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.domain.*;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ContactService extends IDefaultService<Integer, Contact, ContactSearchCriteria> {

    @Cacheable
    SimpleContact findById(Integer contactId, @CacheKey Integer sAccountId);

    @CacheEvict
    @CacheArgs(values = {OpportunityService.class, ContactOpportunityService.class})
    void removeContactOpportunityRelationship(ContactOpportunity associateOpportunity, @CacheKey Integer sAccountId);

    @CacheEvict
    @CacheArgs(values = {OpportunityService.class, ContactOpportunityService.class})
    void saveContactOpportunityRelationship(List<ContactOpportunity> associateOpportunities, @CacheKey Integer accountId);

    @CacheEvict
    @CacheArgs(values = {LeadService.class})
    void saveContactLeadRelationship(List<ContactLead> associateLeads, @CacheKey Integer accountId);

    @CacheEvict
    @CacheArgs(values = {CaseService.class})
    void saveContactCaseRelationship(List<ContactCase> associateCases, @CacheKey Integer accountId);

    @CacheEvict
    @CacheArgs(values = {CaseService.class})
    void removeContactCaseRelationship(ContactCase associateCase, @CacheKey Integer sAccountId);

    @CacheEvict
    @Cacheable
    SimpleContact findContactAssoWithConvertedLead(int leadId, @CacheKey Integer accountId);
}
