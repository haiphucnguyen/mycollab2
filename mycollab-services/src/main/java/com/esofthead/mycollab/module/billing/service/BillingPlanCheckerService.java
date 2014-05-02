package com.esofthead.mycollab.module.billing.service;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public interface BillingPlanCheckerService {
	boolean canCreateNewProject(Integer sAccountId);

	boolean canCreateNewUser(Integer sAccountId);

	boolean canUploadMoreFiles(Integer sAccountId, long extraBytes);
}
