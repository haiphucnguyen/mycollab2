package com.esofthead.mycollab.module.crm.domain;

import java.util.List;

public class SimpleMeeting extends Meeting {
	private static final long serialVersionUID = 1L;

	private String relatedTo;

	private String contactTypeName;

	private String createdUserFullName;

	private List<MeetingInvitee> meetingInvitees;

	public String getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}

	public String getContactTypeName() {
		return contactTypeName;
	}

	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}

	public String getCreatedUserFullName() {
		return createdUserFullName;
	}

	public void setCreatedUserFullName(String createdUserFullName) {
		this.createdUserFullName = createdUserFullName;
	}

	public List<MeetingInvitee> getMeetingInvitees() {
		return meetingInvitees;
	}

	public void setMeetingInvitees(List<MeetingInvitee> meetingInvitees) {
		this.meetingInvitees = meetingInvitees;
	}
}
