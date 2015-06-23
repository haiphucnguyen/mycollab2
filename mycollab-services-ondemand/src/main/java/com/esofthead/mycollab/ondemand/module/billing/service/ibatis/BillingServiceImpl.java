/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.ondemand.module.billing.service.ibatis;

import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.common.i18n.ErrorI18nEnum;
import com.esofthead.mycollab.configuration.PasswordEncryptHelper;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.esb.CamelProxyBuilderUtil;
import com.esofthead.mycollab.i18n.LocalizationHelper;
import com.esofthead.mycollab.module.billing.AccountStatusConstants;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.billing.UserStatusConstants;
import com.esofthead.mycollab.module.billing.esb.AccountDeletedCommand;
import com.esofthead.mycollab.module.billing.esb.BillingEndpoints;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.dao.*;
import com.esofthead.mycollab.module.user.domain.*;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.ondemand.module.billing.AccountPaymentTypeConstants;
import com.esofthead.mycollab.ondemand.module.billing.RegisterSourceConstants;
import com.esofthead.mycollab.ondemand.module.billing.SubdomainExistedException;
import com.esofthead.mycollab.security.PermissionMap;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service(value = "billingService")
public class BillingServiceImpl implements BillingService {
    private static final Logger LOG = LoggerFactory.getLogger(BillingServiceImpl.class);

    private static List<String> ACCOUNT_BLACK_LIST = Arrays.asList("api", "esofthead", "blog", "forum", "wiki", "support", "community");

    @Autowired
    private BillingPlanMapper billingPlanMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

    @Autowired
    private BillingAccountMapperExt billingAccountMapperExt;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public void registerAccount(String subDomain,
                                int billingPlanId, String username,
                                String password, String email, String timezoneId,
                                boolean isEmailVerified) {

        // check subdomain is ascii string
        if (!StringUtils.isAsciiString(subDomain)) {
            throw new UserInvalidInputException(
                    "Subdomain must be an ascii string");
        }

        // check subdomain belong to keyword list
        if (ACCOUNT_BLACK_LIST.contains(subDomain)) {
            throw new SubdomainExistedException(LocalizationHelper.getMessage(
                    LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR,
                    subDomain));
        }

        LOG.debug("Check whether subdomain {} is existed", subDomain);
        BillingAccountExample billingEx = new BillingAccountExample();
        billingEx.createCriteria().andSubdomainEqualTo(subDomain);
        if (this.billingAccountMapper.countByExample(billingEx) > 0) {
            throw new SubdomainExistedException(LocalizationHelper.getMessage(
                    LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR,
                    subDomain));
        }

        BillingPlan billingPlan = this.billingPlanMapper.selectByPrimaryKey(billingPlanId);
        // Save billing account
        LOG.debug("Saving billing account for user {} with subdomain {}", username, subDomain);
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setBillingplanid(billingPlan.getId());
        billingAccount.setCreatedtime(new GregorianCalendar().getTime());
        billingAccount.setPaymentmethod(AccountPaymentTypeConstants.CREDIT_CARD);
        billingAccount.setPricing(billingPlan.getPricing());
        billingAccount.setPricingeffectfrom(new GregorianCalendar().getTime());
        billingAccount.setPricingeffectto(new GregorianCalendar(2099, 11, 31).getTime());
        billingAccount.setStatus(AccountStatusConstants.TRIAL);
        billingAccount.setSubdomain(subDomain);
        billingAccount.setDefaulttimezone(timezoneId);

        this.billingAccountMapper.insertAndReturnKey(billingAccount);
        int accountid = billingAccount.getId();

        // Check whether user has registered to the system before
        String encryptedPassword = PasswordEncryptHelper.encryptSaltPassword(password);
        UserExample ex = new UserExample();
        ex.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(ex);

        Date now = new GregorianCalendar().getTime();

        if (CollectionUtils.isNotEmpty(users)) {
            for (User tmpUser : users) {
                if (!encryptedPassword.equals(tmpUser.getPassword())) {
                    throw new UserInvalidInputException("There is already user " + email
                            + " in the MyCollab database. If it is yours, you must enter the same password you registered to MyCollab. Otherwise " +
                            "you must use the different email.");
                }
            }
        } else {
            // Register the new user to this account
            LOG.debug("Create new user {} in database", username);
            User user = new User();
            user.setEmail(email);
            user.setPassword(encryptedPassword);
            user.setTimezone(timezoneId);
            user.setUsername(username);
            user.setRegisteredtime(now);
            user.setLastaccessedtime(now);

            if (isEmailVerified) {
                user.setStatus(UserStatusConstants.EMAIL_VERIFIED);
            } else {
                user.setStatus(UserStatusConstants.EMAIL_NOT_VERIFIED);
            }

            if (user.getFirstname() == null) {
                user.setFirstname("");
            }

            if (org.apache.commons.lang3.StringUtils.isBlank(user.getLastname())) {
                user.setLastname(StringUtils.extractNameFromEmail(email));
            }
            this.userMapper.insert(user);
        }

        // save default roles
        LOG.debug("Save default roles for account of subdomain {}", subDomain);
        saveEmployeeRole(accountid);
        int adminRoleId = saveAdminRole(accountid);
        saveGuestRole(accountid);

        // save user account
        LOG.debug("Register user {} to subdomain {}", username, subDomain);
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountid(accountid);
        userAccount.setIsaccountowner(true);
        userAccount.setRegisteredtime(now);
        userAccount.setRegisterstatus(RegisterStatusConstants.ACTIVE);
        userAccount.setRegistrationsource(RegisterSourceConstants.WEB);
        userAccount.setRoleid(adminRoleId);
        userAccount.setUsername(username);

        userAccountMapper.insert(userAccount);
    }

    private int saveEmployeeRole(int accountId) {
        // Register default role for account
        final Role role = new Role();
        role.setRolename(SimpleRole.EMPLOYEE);
        role.setDescription("");
        role.setSaccountid(accountId);
        role.setIssystemrole(true);
        int roleId = this.roleService.saveWithSession(role, "");
        this.roleService.savePermission(roleId,
                PermissionMap.buildEmployeePermissionCollection(), accountId);
        return roleId;
    }

    private int saveAdminRole(int accountId) {
        // Register default role for account
        final Role role = new Role();
        role.setRolename(SimpleRole.ADMIN);
        role.setDescription("");
        role.setSaccountid(accountId);
        role.setIssystemrole(true);
        final int roleId = this.roleService.saveWithSession(role, "");

        this.roleService.savePermission(roleId,
                PermissionMap.buildAdminPermissionCollection(), accountId);
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

        this.roleService.savePermission(roleId,
                PermissionMap.buildGuestPermissionCollection(), accountid);
        return roleId;
    }

    @Override
    public List<String> getSubDomainsOfUser(final String username) {
        LOG.debug("Get subdomain of user {}", username);
        return this.billingAccountMapperExt.getSubdomainsOfUser(username);
    }

    @Override
    public List<BillingPlan> getAvailablePlans() {
        return billingPlanMapper.selectByExample(new BillingPlanExample());
    }

    @Override
    public void updateBillingPlan(Integer accountId, int newBillingPlanId) {
        BillingAccount record = new BillingAccount();
        record.setId(accountId);
        record.setBillingplanid(newBillingPlanId);
        billingAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void cancelAccount(Integer accountId,
                              CustomerFeedbackWithBLOBs feedback) {
        AccountDeletedCommand accountDeletedCommand = CamelProxyBuilderUtil
                .build(BillingEndpoints.ACCOUNT_DELETED_ENDPOINT,
                        AccountDeletedCommand.class);
        billingAccountMapper.deleteByPrimaryKey(accountId);
        accountDeletedCommand.accountDeleted(accountId, feedback);
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
            Integer billingId = billingAccount.getBillingplanid();
            return billingPlanMapper.selectByPrimaryKey(billingId);
        } else {
            LOG.error("Can not find billing plan with account {}", sAccountId);
            return getFreeBillingPlan();
        }
    }

    @Override
    public List<BillingAccountWithOwners> getTrialAccountsWithOwners() {
        return billingAccountMapperExt.getTrialAccountsWithOwners();
    }

}
