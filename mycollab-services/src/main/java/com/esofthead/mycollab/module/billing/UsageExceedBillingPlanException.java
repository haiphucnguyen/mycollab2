package com.esofthead.mycollab.module.billing;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class UsageExceedBillingPlanException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsageExceedBillingPlanException() {
		super();
	}
	
	public UsageExceedBillingPlanException(String msg) {
		super(msg);
	}
}
