package com.esofthead.mycollab.module.crm.domain;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class SimpleContactOpportunityRel extends SimpleContact {
	private static final long serialVersionUID = 1L;

	private String roleDecision;

	public String getRoleDecision() {
		return roleDecision;
	}

	public void setRoleDecision(String roleDecision) {
		this.roleDecision = roleDecision;
	}
}
