package com.esofthead.mycollab.module.project.domain;

public class SimpleResource extends Resource {
	private static final long serialVersionUID = 1L;

	private String assignedEmployeeName;
    
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAssignedEmployeeName() {
        return assignedEmployeeName;
    }

    public void setAssignedEmployeeName(String assignedEmployeeName) {
        this.assignedEmployeeName = assignedEmployeeName;
    }
}
