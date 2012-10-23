package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.AccountTypeMapper;
import com.esofthead.mycollab.module.crm.domain.AccountType;
import com.esofthead.mycollab.module.crm.domain.AccountTypeExample;
import com.esofthead.mycollab.module.crm.service.AccountTypeService;

public class AccountTypeServiceImpl extends
		DefaultCrudService<AccountType, Integer> implements AccountTypeService {

	@Override
	public List<AccountType> getAccountTypesByAccountId(int accountid) {
		AccountTypeMapper mapper = (AccountTypeMapper) daoObj;
		AccountTypeExample ex = new AccountTypeExample();
		ex.createCriteria().andSaccountidEqualTo(accountid);
		return mapper.selectByExample(ex);
	}

}
