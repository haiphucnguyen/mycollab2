package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;

public interface BillingAccountMapperExt {
	SimpleBillingAccount getBillingAccountById(int accountId);
}
