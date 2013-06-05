package com.esofthead.mycollab.module.crm.dao;

import java.util.HashMap;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

public interface AccountMapperExt extends ISearchableDAO<AccountSearchCriteria> {

	public abstract SimpleAccount findAccountById(int accountId);
	
	public abstract void updateListAccount(HashMap<String,AccountSearchCriteria> map);
}
