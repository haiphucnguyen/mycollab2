package com.esofthead.mycollab.premium.module.billing.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UnsupportedFeatureException;
import com.esofthead.mycollab.core.cache.CacheKey;
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

		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public List<String> getSubdomainsOfUser(final String username) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public List<BillingPlan> getAvailablePlans() {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void updateBillingPlan(Integer accountid, int newBillingPlanId) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void cancelAccount(Integer accountid,
			CustomerFeedbackWithBLOBs feedback) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
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
		return null;
	}

}