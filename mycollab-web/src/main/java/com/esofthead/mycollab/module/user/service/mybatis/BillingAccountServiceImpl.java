package com.esofthead.mycollab.module.user.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper;
import com.esofthead.mycollab.module.user.domain.Account;
import com.esofthead.mycollab.module.user.service.BillingAccountService;

@Service
public class BillingAccountServiceImpl extends
		DefaultCrudService<Integer, Account> implements BillingAccountService {

	@Autowired
	private BillingAccountMapper billingAccountMapper;

	@Override
	public ICrudGenericDAO<Integer, Account> getCrudMapper() {
		return billingAccountMapper;
	}

}
