package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class GroupItem extends ValuedBean {
	private static final long serialVersionUID = 1L;

	private String groupid;
	private String groupname;
	private int value;
	private String extraValue;
	private int countNum;

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

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
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
