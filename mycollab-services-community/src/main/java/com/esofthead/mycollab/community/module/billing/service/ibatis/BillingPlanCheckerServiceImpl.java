package com.esofthead.mycollab.community.module.billing.service.ibatis;

import com.esofthead.mycollab.module.billing.service.BillingPlanCheckerService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class BillingPlanCheckerServiceImpl implements BillingPlanCheckerService {

	@Override
	public boolean canCreateNewProject(Integer sAccountId) {
		return true;
	}

	@Override
	public boolean canCreateNewUser(Integer sAccountId) {
		return true;
	}

	@Override
	public boolean canUploadMoreFiles(Integer sAccountId, long extraBytes) {
		return true;
	}

}
