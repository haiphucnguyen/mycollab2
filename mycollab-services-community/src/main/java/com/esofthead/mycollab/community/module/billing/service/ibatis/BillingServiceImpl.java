package com.esofthead.mycollab.community.module.billing.service.ibatis;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.localization.ExceptionI18nEnum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.esb.BeanProxyBuilder;
import com.esofthead.mycollab.module.billing.AccountPaymentTypeConstants;
import com.esofthead.mycollab.module.billing.AccountStatusConstants;
import com.esofthead.mycollab.module.billing.ExistingUserRegisterException;
import com.esofthead.mycollab.module.billing.RegisterSourceConstants;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.billing.UserStatusConstants;
import com.esofthead.mycollab.module.billing.esb.AccountDeletedCommand;
import com.esofthead.mycollab.module.billing.esb.BillingEndpoints;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.dao.AccountSettingsMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapper;
import com.esofthead.mycollab.module.user.dao.BillingAccountMapperExt;
import com.esofthead.mycollab.module.user.dao.BillingPlanMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.AccountSettings;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountExample;
import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.BillingPlanExample;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserExample;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.rest.server.signup.ExistingEmailRegisterException;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.PermissionDefItem;
import com.esofthead.mycollab.security.RolePermissionCollections;

@Service(value = "billingService")
public class BillingServiceImpl implements BillingService {
	private static Logger log = LoggerFactory
			.getLogger(BillingServiceImpl.class);

	private static List<String> ACCOUNT_BLACK_LIST = Arrays.asList("api",
			"esofthead");

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

		// check subdomain is ascii string
		if (!StringUtils.isAsciiString(subdomain)) {
			throw new UserInvalidInputException(
					"Subdomain must be an ascii string");
		}

		// check subdomain belong to keyword list
		if (ACCOUNT_BLACK_LIST.contains(subdomain)) {
			throw new SubdomainExistedException(
					LocalizationHelper.getMessage(
							ExceptionI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR,
							subdomain));
		}

		// check whether username is already existed
		log.debug("Check whether username {} is existed", username);
		final UserExample userEx = new UserExample();
		userEx.createCriteria().andUsernameEqualTo(username);
		if (this.userMapper.countByExample(userEx) > 0) {
			throw new ExistingUserRegisterException(
					LocalizationHelper.getMessage(
							ExceptionI18nEnum.EXISTING_USER_REGISTER_ERROR,
							username));
		}

		log.debug("Check whether email {} is existed", email);
		userEx.createCriteria().andUsernameEqualTo(email);
		if (this.userMapper.countByExample(userEx) > 0) {
			throw new ExistingEmailRegisterException(
					LocalizationHelper.getMessage(
							ExceptionI18nEnum.EXISTING_EMAIL_REGISTER_ERROR,
							username));
		}

		log.debug("Check whether subdomain {} is existed", subdomain);
		final BillingAccountExample billingEx = new BillingAccountExample();
		billingEx.createCriteria().andSubdomainEqualTo(subdomain);
		if (this.billingAccountMapper.countByExample(billingEx) > 0) {
			throw new SubdomainExistedException(
					LocalizationHelper.getMessage(
							ExceptionI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR,
							subdomain));
		}

		final BillingPlan billingPlan = this.billingPlanMapper
				.selectByPrimaryKey(billingPlanId);
		// Save billing account
		log.debug("Saving billing account for user {} with subdomain {}",
				username, subdomain);
		final BillingAccount billingAccount = new BillingAccount();
		billingAccount.setBillingplanid(billingPlan.getId());
		billingAccount.setCreatedtime(new GregorianCalendar().getTime());
		billingAccount
				.setPaymentmethod(AccountPaymentTypeConstants.CREDIT_CARD);
		billingAccount.setPricing(billingPlan.getPricing());
		billingAccount.setPricingeffectfrom(new GregorianCalendar().getTime());
		billingAccount.setPricingeffectto(new GregorianCalendar(2099, 12, 31)
				.getTime());
		billingAccount.setStatus(AccountStatusConstants.TRIAL);
		billingAccount.setSubdomain(subdomain);

		this.billingAccountMapper.insertAndReturnKey(billingAccount);
		int accountid = billingAccount.getId();

		// Save to account setting
		log.debug("Save account setting for subdomain domain {}", subdomain);
		final AccountSettings accountSettings = new AccountSettings();
		accountSettings.setSaccountid(accountid);
		accountSettings.setDefaulttimezone(timezoneId);
		this.accountSettingMapper.insert(accountSettings);

		// Register the new user to this account
		log.debug("Create new user {} in database", username);
		final User user = new User();
		user.setEmail(email);
		user.setPassword(PasswordEncryptHelper.encryptSaltPassword(password));
		user.setTimezone(timezoneId);
		user.setUsername(username);
		user.setLastaccessedtime(new GregorianCalendar().getTime());

		if (isEmailVerified) {
			user.setStatus(UserStatusConstants.EMAIL_VERIFIED);
		} else {
			user.setStatus(UserStatusConstants.EMAIL_NOT_VERIFIED);
		}

		if (user.getFirstname() == null) {
			user.setFirstname("");
		}

		if (user.getLastname() == null) {
			user.setLastname("");
		}
		this.userMapper.insert(user);

		// save default roles
		log.debug("Save default roles for account of subdomain {}", subdomain);
		saveEmployeeRole(accountid);
		int adminRoleId = saveAdminRole(accountid);
		saveGuestRole(accountid);

		// save user account
		log.debug("Register user {} to subdomain {}", username, subdomain);
		UserAccount userAccount = new UserAccount();
		userAccount.setAccountid(accountid);
		userAccount.setIsaccountowner(true);
		userAccount.setRegisteredtime(new GregorianCalendar().getTime());
		userAccount.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		userAccount.setRegistrationsource(RegisterSourceConstants.WEB);
		userAccount.setRoleid(adminRoleId);
		userAccount.setUsername(username);

		userAccountMapper.insert(userAccount);
	}

	private int saveEmployeeRole(int accountid) {
		// Register default role for account
		final Role role = new Role();
		role.setRolename(SimpleRole.EMPLOYEE);
		role.setDescription("");
		role.setSaccountid(accountid);
		role.setIssystemrole(true);
		final int roleId = this.roleService.saveWithSession(role, "");

		// save default permission to role
		final PermissionMap permissionMap = new PermissionMap();
		for (final PermissionDefItem element : RolePermissionCollections.CRM_PERMISSIONS_ARR) {
			permissionMap.addPath(element.getKey(),
					AccessPermissionFlag.READ_ONLY);
		}

		for (final PermissionDefItem element : RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
			if (element.getKey().equals(
					RolePermissionCollections.ACCOUNT_BILLING)) {
				permissionMap.addPath(element.getKey(),
						BooleanPermissionFlag.FALSE);
			} else {
				permissionMap.addPath(element.getKey(),
						AccessPermissionFlag.READ_ONLY);
			}
		}

		for (final PermissionDefItem element : RolePermissionCollections.PROJECT_PERMISSION_ARR) {
			permissionMap
					.addPath(element.getKey(), BooleanPermissionFlag.FALSE);
		}

		for (final PermissionDefItem element : RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
			permissionMap.addPath(element.getKey(),
					AccessPermissionFlag.READ_WRITE);
		}

		this.roleService.savePermission(roleId, permissionMap, accountid);
		return roleId;
	}

	private int saveAdminRole(int accountid) {
		// Register default role for account
		final Role role = new Role();
		role.setRolename(SimpleRole.ADMIN);
		role.setDescription("");
		role.setSaccountid(accountid);
		role.setIssystemrole(true);
		final int roleId = this.roleService.saveWithSession(role, "");

		// save default permission to role
		final PermissionMap permissionMap = new PermissionMap();
		for (final PermissionDefItem element : RolePermissionCollections.CRM_PERMISSIONS_ARR) {
			permissionMap
					.addPath(element.getKey(), AccessPermissionFlag.ACCESS);
		}

		for (final PermissionDefItem element : RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
			if (element.getKey().equals(
					RolePermissionCollections.ACCOUNT_BILLING)) {
				permissionMap.addPath(element.getKey(),
						BooleanPermissionFlag.TRUE);
			} else {
				permissionMap.addPath(element.getKey(),
						AccessPermissionFlag.ACCESS);
			}
		}

		for (final PermissionDefItem element : RolePermissionCollections.PROJECT_PERMISSION_ARR) {
			permissionMap.addPath(element.getKey(), BooleanPermissionFlag.TRUE);
		}

		for (final PermissionDefItem element : RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
			permissionMap
					.addPath(element.getKey(), AccessPermissionFlag.ACCESS);
		}

		this.roleService.savePermission(roleId, permissionMap, accountid);
		return roleId;
	}

	private int saveGuestRole(int accountid) {
		// Register default role for account
		final Role role = new Role();
		role.setRolename(SimpleRole.GUEST);
		role.setDescription("");
		role.setSaccountid(accountid);
		role.setIssystemrole(true);
		final int roleId = this.roleService.saveWithSession(role, "");

		// save default permission to role
		final PermissionMap permissionMap = new PermissionMap();
		for (final PermissionDefItem element : RolePermissionCollections.CRM_PERMISSIONS_ARR) {
			permissionMap.addPath(element.getKey(),
					AccessPermissionFlag.NO_ACCESS);
		}

		for (final PermissionDefItem element : RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
			if (element.getKey().equals(
					RolePermissionCollections.ACCOUNT_BILLING)) {
				permissionMap.addPath(element.getKey(),
						BooleanPermissionFlag.FALSE);
			} else {
				permissionMap.addPath(element.getKey(),
						AccessPermissionFlag.NO_ACCESS);
			}
		}

		for (final PermissionDefItem element : RolePermissionCollections.PROJECT_PERMISSION_ARR) {
			permissionMap
					.addPath(element.getKey(), BooleanPermissionFlag.FALSE);
		}

		for (final PermissionDefItem element : RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
			permissionMap.addPath(element.getKey(),
					AccessPermissionFlag.NO_ACCESS);
		}

		this.roleService.savePermission(roleId, permissionMap, accountid);
		return roleId;
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
		BillingAccount record = new BillingAccount();
		record.setId(accountid);
		record.setBillingplanid(newBillingPlanId);
		billingAccountMapper.updateByPrimaryKeySelective(record);
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