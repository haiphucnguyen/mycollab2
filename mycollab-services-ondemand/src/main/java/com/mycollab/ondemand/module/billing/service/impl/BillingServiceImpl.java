package com.mycollab.ondemand.module.billing.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.i18n.LocalizationHelper;
import com.mycollab.module.billing.AccountStatusConstants;
import com.mycollab.module.user.dao.BillingAccountMapper;
import com.mycollab.module.user.dao.BillingPlanMapper;
import com.mycollab.module.user.domain.*;
import com.mycollab.module.user.service.BillingAccountService;
import com.mycollab.ondemand.module.billing.ExistedSubDomainException;
import com.mycollab.ondemand.module.billing.dao.BillingAccountMapperExt2;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;
import com.mycollab.ondemand.module.billing.esb.DeleteAccountEvent;
import com.mycollab.ondemand.module.billing.esb.DeleteSubscriptionEvent;
import com.mycollab.ondemand.module.billing.esb.UpdateBillingPlanEvent;
import com.mycollab.ondemand.module.billing.service.BillingService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
@Service
public class BillingServiceImpl implements BillingService {
    private static List<String> ACCOUNT_BLACK_LIST = Arrays.asList("api", "esofthead", "blog", "forum", "wiki", "support", "community");

    @Autowired
    private BillingAccountMapperExt2 billingAccountMapperExt2;

    @Autowired
    private BillingPlanMapper billingPlanMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

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

        // check subDomain is ascii string
        if (!StringUtils.isAsciiString(subDomain)) {
            throw new UserInvalidInputException("Subdomain must be an ascii string");
        }

        // check subDomain belong to keyword list
        if (ACCOUNT_BLACK_LIST.contains(subDomain)) {
            throw new ExistedSubDomainException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }

        BillingAccountExample billingEx = new BillingAccountExample();
        billingEx.createCriteria().andSubdomainEqualTo(subDomain);
        if (this.billingAccountMapper.countByExample(billingEx) > 0) {
            throw new ExistedSubDomainException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }

        BillingPlan billingPlan = billingPlanMapper.selectByPrimaryKey(billingPlanId);
        // Save billing account
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setBillingplanid(billingPlan.getId());
        billingAccount.setCreatedtime(new GregorianCalendar().getTime());
        billingAccount.setStatus(AccountStatusConstants.TRIAL);
        billingAccount.setSubdomain(subDomain);
        billingAccount.setDisplayemailpublicly(true);
        billingAccount.setDefaulttimezone(timezoneId);

        try {
            billingAccountMapper.insertAndReturnKey(billingAccount);
        } catch (DuplicateKeyException e) {
            throw new ExistedSubDomainException(LocalizationHelper.getMessage(LocalizationHelper.defaultLocale,
                    ErrorI18nEnum.EXISTING_DOMAIN_REGISTER_ERROR, subDomain));
        }
        int accountId = billingAccount.getId();
        billingAccountService.createDefaultAccountData(username, password, timezoneId, LocalizationHelper
                .defaultLocale.getLanguage(), isEmailVerified, true, accountId);
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
    public List<SimpleBillingAccount2> findPageableListByCriteria(BasicSearchRequest<BillingAccountSearchCriteria> searchRequest) {
        return billingAccountMapperExt2.findPageableListByCriteria(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                        searchRequest.getNumberOfItems()));
    }

    @Override
    public void updateBillingPlan(@CacheKey Integer accountId, BillingPlan oldPlan, BillingPlan newPlan) {
        BillingAccount record = new BillingAccount();
        record.setId(accountId);
        record.setBillingplanid(newPlan.getId());
        billingAccountMapper.updateByPrimaryKeySelective(record);

        asyncEventBus.post(new UpdateBillingPlanEvent(accountId, newPlan));
    }

    @Override
    public List<BillingPlan> getAvailablePlans() {
        BillingPlanExample ex = new BillingPlanExample();
        return billingPlanMapper.selectByExample(ex);
    }

    @Override
    public List<String> getSubDomainsOfUser(String username) {
        return this.billingAccountMapperExt2.getSubDomainsOfUser(username);
    }

    public List<BillingAccountWithOwners> getTrialAccountsWithOwners() {
        return billingAccountMapperExt2.getTrialAccountsWithOwners();
    }

    @Override
    public BillingPlan findBillingPlan(@CacheKey Integer sAccountId) {
        BillingAccount billingAccount = billingAccountMapper.selectByPrimaryKey(sAccountId);
        if (billingAccount != null) {
            Integer billingId = billingAccount.getBillingplanid();
            return billingPlanMapper.selectByPrimaryKey(billingId);
        } else {
            throw new MyCollabException("Can not find the billing plan for account " + sAccountId);
        }
    }
}
