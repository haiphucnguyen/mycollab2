package com.mycollab.ondemand.module.billing.service.ibatis;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.i18n.LocalizationHelper;
import com.mycollab.module.billing.AccountStatusConstants;
import com.mycollab.module.billing.service.BillingService;
import com.mycollab.module.user.dao.BillingAccountMapper;
import com.mycollab.module.user.dao.BillingAccountMapperExt;
import com.mycollab.module.user.dao.BillingPlanMapper;
import com.mycollab.module.user.domain.*;
import com.mycollab.module.user.service.BillingAccountService;
import com.mycollab.ondemand.module.billing.AccountPaymentTypeConstants;
import com.mycollab.ondemand.module.billing.SubDomainExistedException;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample;
import com.mycollab.ondemand.module.billing.esb.DeleteAccountEvent;
import com.mycollab.ondemand.module.billing.esb.DeleteSubscriptionEvent;
import com.mycollab.ondemand.module.billing.esb.UpdateBillingPlanEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    private BillingAccountService billingAccountService;

    @Autowired
    private BillingSubscriptionMapper billingSubscriptionMapper;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    @Transactional
    public void registerAccount(String subDomain, int billingPlanId, String username, String password, String email, String timezoneId,
                                boolean isEmailVerified) {

        // check subdomain is ascii string
        if (!StringUtils.isAsciiString(subDomain)) {
            throw new UserInvalidInputException("Subdomain must be an ascii string");
        }

        // check subdomain belong to keyword list
        if (ACCOUNT_BLACK_LIST.contains(subDomain)) {
            throw new SubDomainExistedException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }

        LOG.debug("Check whether subdomain {} is existed", subDomain);
        BillingAccountExample billingEx = new BillingAccountExample();
        billingEx.createCriteria().andSubdomainEqualTo(subDomain);
        if (this.billingAccountMapper.countByExample(billingEx) > 0) {
            throw new SubDomainExistedException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }

        BillingPlan billingPlan = billingPlanMapper.selectByPrimaryKey(billingPlanId);
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
        billingAccount.setDisplayemailpublicly(true);
        billingAccount.setDefaulttimezone(timezoneId);

        try {
            billingAccountMapper.insertAndReturnKey(billingAccount);
        } catch (DuplicateKeyException e) {
            throw new SubDomainExistedException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }
        int accountId = billingAccount.getId();
        billingAccountService.createDefaultAccountData(username, password, timezoneId, LocalizationHelper
                .defaultLocale.getLanguage(), isEmailVerified, true, accountId);
    }

    @Override
    public List<String> getSubDomainsOfUser(final String username) {
        LOG.debug("Get subdomain of user {}", username);
        return this.billingAccountMapperExt.getSubdomainsOfUser(username);
    }

    @Override
    public List<BillingPlan> getAvailablePlans() {
        BillingPlanExample ex = new BillingPlanExample();
        return billingPlanMapper.selectByExample(ex);
    }

    @Override
    public void updateBillingPlan(Integer accountId, int newBillingPlanId) {
        BillingAccount record = new BillingAccount();
        record.setId(accountId);
        record.setBillingplanid(newBillingPlanId);
        billingAccountMapper.updateByPrimaryKeySelective(record);

        UpdateBillingPlanEvent event = new UpdateBillingPlanEvent(accountId);

    }

    @Override
    public void cancelAccount(Integer accountId, CustomerFeedbackWithBLOBs feedback) {
        BillingSubscriptionExample subscriptionExample = new BillingSubscriptionExample();
        subscriptionExample.createCriteria().andAccountidEqualTo(accountId);
        List<BillingSubscription> billingSubscriptions = billingSubscriptionMapper.selectByExample(subscriptionExample);
        DeleteSubscriptionEvent deleteSubscriptionEvent = new DeleteSubscriptionEvent(billingSubscriptions);
        asyncEventBus.post(deleteSubscriptionEvent);

        billingAccountMapper.deleteByPrimaryKey(accountId);
        DeleteAccountEvent event = new DeleteAccountEvent(accountId, feedback);
        asyncEventBus.post(event);
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
        BillingAccount billingAccount = billingAccountMapper.selectByPrimaryKey(sAccountId);
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
