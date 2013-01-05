package com.esofthead.mycollab.module.crm.domain;

public class SimpleCall extends Call {

    private static final long serialVersionUID = 1L;
    private String relatedTo;
    private String assignUserFullName;

    public String getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getAssignUserFullName() {
        return assignUserFullName;
    }

    public void setAssignUserFullName(String assignUserFullName) {
        this.assignUserFullName = assignUserFullName;
    }
}
