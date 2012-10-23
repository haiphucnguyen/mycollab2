package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

public interface AccountMapperExt extends AccountMapper {
	
	List<SimpleAccount> findPagableList(
			AccountSearchCriteria criteria, RowBounds rowBounds);

	int getTotalCount(AccountSearchCriteria criteria);

	SimpleAccount findAccountById(int accountId);
}
