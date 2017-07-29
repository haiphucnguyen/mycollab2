package com.mycollab.module.billing.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.billing.UsageExceedBillingPlanException;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@IgnoreCacheClass
public interface BillingPlanCheckerService extends IService {
    void validateAccountCanCreateMoreProject(Integer sAccountId) throws UsageExceedBillingPlanException;

    void validateAccountCanCreateNewUser(Integer sAccountId) throws UsageExceedBillingPlanException;

    void validateAccountCanUploadMoreFiles(Integer sAccountId, long extraBytes) throws UsageExceedBillingPlanException;
}
