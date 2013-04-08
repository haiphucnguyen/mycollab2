package com.esofthead.mycollab.module.user.service;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.user.domain.Account;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;

public interface BillingAccountService extends ICrudService<Integer, Account> {
	SimpleBillingAccount getBillingAccountById(int accountId);
}
