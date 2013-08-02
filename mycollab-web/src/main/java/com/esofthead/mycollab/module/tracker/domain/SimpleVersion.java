package com.esofthead.mycollab.module.tracker.domain;

public class SimpleVersion extends Version {
	private static final long serialVersionUID = 1L;

	private int numOpenBugs;

	private int numBugs;

	public int getNumOpenBugs() {
		return numOpenBugs;
	}

	public void setNumOpenBugs(int numOpenBugs) {
		this.numOpenBugs = numOpenBugs;
	}

	public int getNumBugs() {
		return numBugs;
	}

	public void setNumBugs(int numBugs) {
		this.numBugs = numBugs;
	}
}
