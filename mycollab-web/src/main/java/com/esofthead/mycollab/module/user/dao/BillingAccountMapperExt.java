package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.module.user.domain.Account;

public interface BillingAccountMapperExt {
	void insertAndReturnKey(Account account);
}
