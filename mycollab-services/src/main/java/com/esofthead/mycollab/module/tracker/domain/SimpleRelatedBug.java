package com.esofthead.mycollab.module.tracker.domain;

public class SimpleRelatedBug extends RelatedBug {

	private static final long serialVersionUID = 1L;
	
	private String bugName;

	public void setBugName(String bugName) {
		this.bugName = bugName;
	}

	public String getBugName() {
		return bugName;
	}

}
