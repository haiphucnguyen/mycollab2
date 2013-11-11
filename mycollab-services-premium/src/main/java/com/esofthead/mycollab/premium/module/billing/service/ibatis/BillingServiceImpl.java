package com.esofthead.mycollab.premium.module.billing.service.ibatis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.esb.BeanProxyBuilder;
import com.esofthead.mycollab.module.billing.esb.AccountDeletedCommand;
import com.esofthead.mycollab.module.billing.esb.BillingEndpoints;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.dao.AccountSettingsMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapperExt;
import com.esofthead.mycollab.module.user.dao.BillingPlanMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.BillingPlanExample;
import com.esofthead.mycollab.module.user.service.RoleService;

@Service(value = "billingService")
public class BillingServiceImpl implements BillingService {
	private static Logger log = LoggerFactory
			.getLogger(BillingServiceImpl.class);

	@Autowired
	private BillingPlanMapper billingPlanMapper;

	@Autowired
	private BillingAccountMapper billingAccountMapper;

	@Autowired
	private BillingAccountMapperExt billingAccountMapperExt;

	@Autowired
	private AccountSettingsMapper accountSettingMapper;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleService roleService;

	@Override
	@Transactional
	public void registerAccount(final String subdomain,
			final int billingPlanId, final String username,
			final String password, final String email, final String timezoneId,
			boolean isEmailVerified) {

		throw new MyCollabException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public List<String> getSubdomainsOfUser(final String username) {
		log.debug("Get subdomain of user {}", username);
		return this.billingAccountMapperExt.getSubdomainsOfUser(username);
	}

	@Override
	public List<BillingPlan> getAvailablePlans() {
		return billingPlanMapper.selectByExample(new BillingPlanExample());
	}

	@Override
	public void updateBillingPlan(Integer accountid, int newBillingPlanId) {
		throw new MyCollabException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void cancelAccount(Integer accountid) {
		AccountDeletedCommand accountDeletedCommand = new BeanProxyBuilder()
				.build(BillingEndpoints.ACCOUNT_DELETED_ENDPOINT,
						AccountDeletedCommand.class);
		billingAccountMapper.deleteByPrimaryKey(accountid);
		accountDeletedCommand.accountDeleted(accountid);
	}

	@Override
	public BillingPlan getFreeBillingPlan() {
		BillingPlanExample ex = new BillingPlanExample();
		ex.createCriteria().andBillingtypeEqualTo("Free");
		List<BillingPlan> billingPlans = billingPlanMapper.selectByExample(ex);
		if (billingPlans != null && billingPlans.size() == 1) {
			return billingPlans.get(0);
		} else {
			throw new MyCollabException("Can not query free billing plan");
		}
	}

	@Override
	public BillingPlan findBillingPlan(@CacheKey Integer sAccountId) {
		BillingAccount billingAccount = billingAccountMapper
				.selectByPrimaryKey(sAccountId);
		if (billingAccount != null) {
			Integer billingplanid = billingAccount.getBillingplanid();
			return billingPlanMapper.selectByPrimaryKey(billingplanid);
		}
		return null;
	}

	@Override
	public List<BillingAccountWithOwners> getTrialAccountsWithOwners() {
		return billingAccountMapperExt.getTrialAccountsWithOwners();
	}

}