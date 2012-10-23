package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.crm.domain.AccountType;

public interface AccountTypeService extends ICrudService<AccountType, Integer> {
	List<AccountType> getAccountTypesByAccountId(int accountid);
}
