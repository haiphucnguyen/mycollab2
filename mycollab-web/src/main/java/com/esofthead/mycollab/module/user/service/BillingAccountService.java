package com.esofthead.mycollab.module.user.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.user.domain.Account;

public interface BillingAccountService extends ICrudService<Account, Integer> {
	int insertAndReturnKey(Account account);
}
