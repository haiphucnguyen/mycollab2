package com.esofthead.mycollab.module.billing.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingInclude;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;

public interface BillingService extends IService {
    void registerAccount(String subdomain, int billingPlanId, String username,
            String password, String email, String timezoneId);

    void cancelAccount(int accountid);

    void updateBillingPlan(int accountid, int newBillingPlanId);

    @RemotingInclude
    List<String> getSubdomainsOfUser(String username);

    List<BillingPlan> getAvailablePlans();
}
