package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheArgs;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.AccountLead;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface AccountService extends IDefaultService<Integer, Account, AccountSearchCriteria> {

    @Cacheable
    SimpleAccount findById(Integer id, @CacheKey Integer accountId);

    @CacheEvict
    @CacheArgs(values = {LeadService.class})
    void saveAccountLeadRelationship(List<AccountLead> associateLeads, @CacheKey Integer accountId);

    @CacheEvict
    @CacheArgs(values = {LeadService.class})
    void removeAccountLeadRelationship(AccountLead associateLead, @CacheKey Integer accountId);

    @Cacheable
    SimpleAccount findAccountAssoWithConvertedLead(Integer leadId, @CacheKey Integer accountId);
}
