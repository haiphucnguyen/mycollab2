package com.mycollab.premium.module.billing.service.impl;

import com.mycollab.module.billing.UsageExceedBillingPlanException;
import com.mycollab.module.billing.service.BillingPlanCheckerService;
import com.mycollab.module.user.service.UserService;
import com.mycollab.premium.license.service.LicenseResolver;
import com.mycollab.pro.license.LicenseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {
    @Autowired
    private LicenseResolver licenseResolver;

    @Autowired
    private UserService userService;

    @Override
    public void validateAccountCanCreateMoreProject(Integer sAccountId) throws UsageExceedBillingPlanException {

    }

    @Override
    public void validateAccountCanCreateNewUser(Integer sAccountId) throws UsageExceedBillingPlanException {
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
        int numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId);
        if (licenseInfo != null) {
            if (numOfUsers >= licenseInfo.getMaxUsers()) {
                throw new UsageExceedBillingPlanException();
            }
        }
    }

    @Override
    public void validateAccountCanUploadMoreFiles(Integer sAccountId, long extraBytes) throws UsageExceedBillingPlanException {
    }
}
