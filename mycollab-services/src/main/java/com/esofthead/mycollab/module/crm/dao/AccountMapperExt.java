package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

public interface AccountMapperExt extends
		ISearchableDAO<AccountSearchCriteria>,
		IMassUpdateDAO<Account, AccountSearchCriteria> {

	public abstract SimpleAccount findById(int accountId);
}
