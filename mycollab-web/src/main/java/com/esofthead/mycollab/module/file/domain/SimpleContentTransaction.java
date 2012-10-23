package com.esofthead.mycollab.module.file.domain;

public class SimpleContentTransaction extends ContentTransaction {
	private String ownerFullName;

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}
}
