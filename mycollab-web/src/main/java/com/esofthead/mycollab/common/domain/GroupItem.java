package com.esofthead.mycollab.common.domain;

public class GroupItem {

    private String groupid;
    private String groupname;
    private int value;
    private String extraValue;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

	public String getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(String extraValue) {
		this.extraValue = extraValue;
	}
}
