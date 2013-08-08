package com.esofthead.mycollab.module.user.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.configuration.DeploymentMode;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapperExt;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountExample;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;

@Service
public class BillingAccountServiceImpl extends
		DefaultCrudService<Integer, BillingAccount> implements
		BillingAccountService {

	@Autowired
	private BillingAccountMapper billingAccountMapper;

	@Autowired
	private BillingAccountMapperExt billingAccountMapperExt;

	@Override
	public ICrudGenericDAO<Integer, BillingAccount> getCrudMapper() {
		return billingAccountMapper;
	}

	@Override
	public SimpleBillingAccount getBillingAccountById(int accountId) {
		return billingAccountMapperExt.getBillingAccountById(accountId);
	}

	@Override
	public BillingAccount getAccountByDomain(String domain) {
		BillingAccountExample ex = new BillingAccountExample();

		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			ex.createCriteria().andSubdomainEqualTo(domain);
		}

		List<BillingAccount> accounts = billingAccountMapper
				.selectByExample(ex);
		if ((accounts == null) || accounts.size() == 0) {
			return null;
		} else {
			return accounts.get(0);
		}
	}

}
