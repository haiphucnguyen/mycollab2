package com.esofthead.mycollab.ondemand.module.billing.service.ibatis;

import com.esofthead.mycollab.module.billing.service.BillingPlanCheckerService;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {

	@Override
	public boolean canCreateNewProject(Integer sAccountId) {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		Integer numOfActiveProjects = projectService
				.getTotalActiveProjectsInAccount(sAccountId);

		return (numOfActiveProjects < billingPlan.getNumprojects());
	}

	@Override
	public boolean canCreateNewUser(Integer sAccountId) {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		int numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId);
		return (numOfUsers < billingPlan.getNumusers());
	}

	@Override
	public boolean canUploadMoreFiles(Integer sAccountId, long extraBytes) {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		DriveInfoService driveInfoService = ApplicationContextUtil
				.getSpringBean(DriveInfoService.class);
		DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);
		return (driveInfo.getUsedvolume() + extraBytes <= billingPlan
				.getVolume());
	}

}
