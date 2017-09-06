package com.mycollab.community.module.billing.service.impl;

import com.mycollab.module.billing.UsageExceedBillingPlanException;
import com.mycollab.module.billing.service.BillingPlanCheckerService;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {

    @Override
    public void validateAccountCanCreateMoreProject(Integer sAccountId) throws UsageExceedBillingPlanException {

    }

    @Override
    public void validateAccountCanCreateNewUser(Integer sAccountId) throws UsageExceedBillingPlanException {
    }

    @Override
    public void validateAccountCanUploadMoreFiles(Integer sAccountId, long extraBytes) throws UsageExceedBillingPlanException {

    }

}
