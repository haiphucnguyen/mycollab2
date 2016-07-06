package com.mycollab.ondemand.module.billing.service.ibatis;

import com.mycollab.module.billing.UsageExceedBillingPlanException;
import com.mycollab.module.billing.service.BillingPlanCheckerService;
import com.mycollab.module.billing.service.BillingService;
import com.mycollab.module.ecm.domain.DriveInfo;
import com.mycollab.module.ecm.service.DriveInfoService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.module.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {
    private static final Logger LOG = LoggerFactory.getLogger(BillingPlanCheckerServiceImpl.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriveInfoService driveInfoService;

    @Override
    public void validateAccountCanCreateMoreProject(Integer sAccountId) throws UsageExceedBillingPlanException {
        BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);
        Integer numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(sAccountId);

        if (numOfActiveProjects >= billingPlan.getNumprojects()) {
            throw new UsageExceedBillingPlanException();
        }
    }

    @Override
    public void validateAccountCanCreateNewUser(Integer sAccountId) throws UsageExceedBillingPlanException {
        BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);
        int numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId);
        if (numOfUsers >= billingPlan.getNumusers()) {
            throw new UsageExceedBillingPlanException();
        }
    }

    @Override
    public void validateAccountCanUploadMoreFiles(Integer sAccountId, long extraBytes) throws UsageExceedBillingPlanException {
        BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);
        if (billingPlan == null) {
            LOG.error("Can not define the billing plan for account ", sAccountId);
            return;
        }

        DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);
        if (driveInfo.getUsedvolume() + extraBytes >= billingPlan.getVolume()) {
            throw new UsageExceedBillingPlanException();
        }
    }
}
