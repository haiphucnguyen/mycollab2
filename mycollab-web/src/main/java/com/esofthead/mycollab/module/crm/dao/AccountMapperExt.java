package com.esofthead.mycollab.module.crm.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

public interface AccountMapperExt extends ISearchableDAO<AccountSearchCriteria> {

	public abstract SimpleAccount findAccountById(int accountId);
	
	public abstract void updateListAccount(HashMap<String,AccountSearchCriteria> map);
	
	public abstract void upateAccountBySearchCriteria(@Param("record") Account record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
