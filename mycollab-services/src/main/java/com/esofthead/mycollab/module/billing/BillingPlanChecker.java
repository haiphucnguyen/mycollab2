package com.esofthead.mycollab.module.billing;

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class BillingPlanChecker {

	public static boolean canCreateNewProject(int sAccountId) {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		Integer numOfActiveProjects = projectService
				.getTotalActiveProjectsInAccount(sAccountId);

		return (numOfActiveProjects < billingPlan.getNumprojects());
	}

	public static boolean canCreateNewUser(int sAccountId) {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		int numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId);
		return (numOfUsers < billingPlan.getNumusers());
	}

	public static boolean canUploadMoreFiles(int sAccountId, long extraBytes) {
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
