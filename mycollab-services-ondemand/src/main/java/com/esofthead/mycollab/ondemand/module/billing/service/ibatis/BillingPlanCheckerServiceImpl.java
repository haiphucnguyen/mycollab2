package com.esofthead.mycollab.ondemand.module.billing.service.ibatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.billing.UsageExceedBillingPlanException;
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
@Service
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {
	private static Logger log = LoggerFactory
			.getLogger(BillingPlanCheckerServiceImpl.class);

	@Override
	public void validateAccountCanCreateMoreProject(Integer sAccountId)
			throws UsageExceedBillingPlanException {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		Integer numOfActiveProjects = projectService
				.getTotalActiveProjectsInAccount(sAccountId);

		if (numOfActiveProjects >= billingPlan.getNumprojects()) {
			throw new UsageExceedBillingPlanException();
		}
	}

	@Override
	public void validateAccountCanCreateNewUser(Integer sAccountId)
			throws UsageExceedBillingPlanException {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);

		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		int numOfUsers = userService.getTotalActiveUsersInAccount(sAccountId);
		if (numOfUsers >= billingPlan.getNumusers()) {
			throw new UsageExceedBillingPlanException();
		}

	}

	@Override
	public void validateAccountCanUploadMoreFiles(Integer sAccountId,
			long extraBytes) throws UsageExceedBillingPlanException {
		BillingService billingService = ApplicationContextUtil
				.getSpringBean(BillingService.class);
		BillingPlan billingPlan = billingService.findBillingPlan(sAccountId);
		if (billingPlan == null) {
			log.error("Can not define the billing plan for account ",
					sAccountId);
			return;
		}

		DriveInfoService driveInfoService = ApplicationContextUtil
				.getSpringBean(DriveInfoService.class);
		DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);
		if (driveInfo.getUsedvolume() + extraBytes >= billingPlan.getVolume()) {
			throw new UsageExceedBillingPlanException();
		}

	}
}
