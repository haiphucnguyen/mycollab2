package com.esofthead.mycollab.module.crm.domain;


public class SimpleCase extends CaseWithBLOBs {
	private static final long serialVersionUID = 1L;

	private String assignUserAvatarId;
	
	private String assignUserFullName;

    private String accountName;

    public String getAssignUserFullName() {
		return assignUserFullName;
	}

	public void setAssignUserFullName(String assignUserFullName) {
		this.assignUserFullName = assignUserFullName;
	}

	public String getAssignUserAvatarId() {
		return assignUserAvatarId;
	}

	public void setAssignUserAvatarId(String assignUserAvatarId) {
		this.assignUserAvatarId = assignUserAvatarId;
	}

	public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
