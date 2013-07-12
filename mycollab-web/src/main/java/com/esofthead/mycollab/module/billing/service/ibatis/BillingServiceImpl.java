package com.esofthead.mycollab.module.billing.service.ibatis;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.dao.AccountCurrencyMapper;
import com.esofthead.mycollab.common.domain.AccountCurrency;
import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.billing.AccountPaymentTypeConstants;
import com.esofthead.mycollab.module.billing.AccountStatusConstants;
import com.esofthead.mycollab.module.billing.ExistingUserRegisterException;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.PermissionFlag;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.dao.AccountSettingsMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapperExt;
import com.esofthead.mycollab.module.user.dao.BillingPlanMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.AccountSettings;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountExample;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.rest.server.signup.ExistingEmailRegisterException;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;
import com.esofthead.mycollab.web.LocalizationHelper;

@Service
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
	private UserMapper userMapper;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountCurrencyMapper accountCurrencyMapper;

	@Override
	@Transactional
	public void registerAccount(final String subdomain,
			final int billingPlanId, final String username,
			final String password, final String email, final String timezoneId) {

		// check whether username is already existed
		final UserExample userEx = new UserExample();
		userEx.createCriteria().andUsernameEqualTo(username);
		if (this.userMapper.countByExample(userEx) > 0) {
			throw new ExistingUserRegisterException(
					LocalizationHelper.getMessage(
							GenericI18Enum.EXISTING_USER_REGISTER_ERROR,
							username));
		}

		userEx.createCriteria().andUsernameEqualTo(email);
		if (this.userMapper.countByExample(userEx) > 0) {
			throw new ExistingEmailRegisterException(
					LocalizationHelper.getMessage(
							GenericI18Enum.EXISTING_EMAIL_REGISTER_ERROR,
							username));
		}

		final BillingAccountExample billingEx = new BillingAccountExample();
		billingEx.createCriteria().andSubdomainEqualTo(subdomain);
		if (this.billingAccountMapper.countByExample(billingEx) > 0) {
			throw new SubdomainExistedException(LocalizationHelper.getMessage(
					GenericI18Enum.EXISTING_DOMAIN_REGISTER_ERROR, subdomain));
		}

		final BillingPlan billingPlan = this.billingPlanMapper
				.selectByPrimaryKey(billingPlanId);
		// Save billing account
		final BillingAccount billingAccount = new BillingAccount();
		billingAccount.setBillingplanid(billingPlan.getId());
		billingAccount.setCreatedtime(new GregorianCalendar().getTime());
		billingAccount
				.setPaymentmethod(AccountPaymentTypeConstants.CREDIT_CARD);
		billingAccount.setPricing(billingPlan.getPricing());
		billingAccount.setPricingeffectfrom(new GregorianCalendar().getTime());
		billingAccount.setPricingeffectto(new GregorianCalendar(2099, 12, 31)
				.getTime());
		billingAccount.setStatus(AccountStatusConstants.ACTIVE);
		billingAccount.setSubdomain(subdomain);

		final Integer accountid = this.billingAccountMapper
				.insertAndReturnKey(billingAccount);

		// Save to account setting
		final AccountSettings accountSettings = new AccountSettings();
		accountSettings.setSaccountid(accountid);
		accountSettings.setDefaulttimezone(timezoneId);
		this.accountSettingMapper.insert(accountSettings);

		// Register the new user to this account
		// Fix issue account
		final User user = new User();
		user.setEmail(email);
		user.setPassword(PasswordEncryptHelper.encryptSaltPassword(password));
		user.setTimezone(timezoneId);
		user.setUsername(username);
		user.setLastaccessedtime(new GregorianCalendar().getTime());

		if (user.getFirstname() == null && user.getLastname() == null) {
			user.setFirstname(username);
			user.setLastname("");
		} else if (user.getFirstname() == null) {
			user.setFirstname("");
		} else if (user.getLastname() == null) {
			user.setLastname("");
		}
		this.userMapper.insert(user);

		// TODO: adjust register service
		// user.setRegisterstatus(RegisterStatusConstants.VERIFICATING);
		// user.setRegistrationsource(RegisterSourceConstants.WEB);

		// Register default role for account
		final Role role = new Role();
		role.setRolename(SimpleRole.STANDARD_USER);
		role.setDescription("");
		role.setSaccountid(accountid);
		final int roleId = this.roleService.saveWithSession(role, username);

		// save default permission to role
		final PermissionMap permissionMap = new PermissionMap();
		for (final String element : RolePermissionCollections.CRM_PERMISSIONS_ARR) {
			permissionMap.addPath(element, PermissionFlag.READ_ONLY);
		}

		for (final String element : RolePermissionCollections.USER_PERMISSION_ARR) {
			permissionMap.addPath(element, PermissionFlag.READ_ONLY);
		}
		this.roleService.savePermission(roleId, permissionMap);

		// save default account currency
		final AccountCurrency currency = new AccountCurrency();
		currency.setAccountid(accountid);
		currency.setCurrencyid(1);
		this.accountCurrencyMapper.insert(currency);
	}

	@Override
	public List<String> getSubdomainsOfUser(final String username) {
		return this.billingAccountMapperExt.getSubdomainsOfUser(username);
	}

}
