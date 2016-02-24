package com.esofthead.mycollab.premium.module.billing.service.ibatis;

import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.module.billing.UsageExceedBillingPlanException;
import com.esofthead.mycollab.module.billing.service.BillingPlanCheckerService;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {
    @Autowired
    private BillingService billingService;

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
