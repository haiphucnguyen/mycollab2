package com.esofthead.mycollab.module.tracker.domain;

public class SimpleComponent extends Component {
	private static final long serialVersionUID = 1L;

	private String userLeadAvatarId;

	private String userLeadFullName;

	private int numOpenBugs;

	private int numBugs;

	public String getUserLeadFullName() {
		return userLeadFullName;
	}

	public void setUserLeadFullName(String userLeadFullName) {
		this.userLeadFullName = userLeadFullName;
	}

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

	public String getUserLeadAvatarId() {
		return userLeadAvatarId;
	}

	public void setUserLeadAvatarId(String userLeadAvatarId) {
		this.userLeadAvatarId = userLeadAvatarId;
	}
}
