package com.esofthead.mycollab.module.user.service.mybatis;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapperExt;
import com.esofthead.mycollab.module.user.domain.Account;
import com.esofthead.mycollab.module.user.service.BillingAccountService;

public class BillingAccountServiceImpl extends DefaultCrudService<Account, Integer>
		implements BillingAccountService {

	private BillingAccountMapperExt billingAccountExtDAO;

	public void setBillingAccountExtDAO(BillingAccountMapperExt billingAccountExtDAO) {
		this.billingAccountExtDAO = billingAccountExtDAO;
	}

	@Override
	public int insertAndReturnKey(Account account) {
		billingAccountExtDAO.insertAndReturnKey(account);
		return account.getId();
	}

}
