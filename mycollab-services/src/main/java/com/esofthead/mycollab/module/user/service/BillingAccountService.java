package com.esofthead.mycollab.module.user.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;

public interface BillingAccountService extends ICrudService<Integer, BillingAccount> {
	SimpleBillingAccount getBillingAccountById(int accountId);
	
	BillingAccount getAccountByDomain(String domain);
	
	@Cacheable
	BillingAccount getAccountById(@CacheKey Integer accountId);
}
