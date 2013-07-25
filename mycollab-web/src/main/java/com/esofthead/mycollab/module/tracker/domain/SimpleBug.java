package com.esofthead.mycollab.module.tracker.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SimpleBug extends BugWithBLOBs {

	private static final long serialVersionUID = 1L;
	private String loguserFullName;
	private String loguserAvatarId;
	private String assignUserAvatarId;
	private String assignuserFullName;
	private String projectname;
	private List<Version> affectedVersions;
	private List<Version> fixedVersions;
	private List<Component> components;
	private String comment;
	private String milestoneName;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getLoguserFullName() {
		return loguserFullName;
	}

	public void setLoguserFullName(String loguserFullName) {
		this.loguserFullName = loguserFullName;
	}

	public String getAssignuserFullName() {
		return assignuserFullName;
	}

	public void setAssignuserFullName(String assignuserFullName) {
		this.assignuserFullName = assignuserFullName;
	}

	public List<Version> getAffectedVersions() {
		return affectedVersions;
	}

	public void setAffectedVersions(List<Version> affectedVersions) {
		this.affectedVersions = affectedVersions;
	}

	public List<Version> getFixedVersions() {
		return fixedVersions;
	}

	public void setFixedVersions(List<Version> fixedVersions) {
		this.fixedVersions = fixedVersions;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public String getAssignUserAvatarId() {
		return assignUserAvatarId;
	}

	public void setAssignUserAvatarId(String assignUserAvatarId) {
		this.assignUserAvatarId = assignUserAvatarId;
	}

	public String getLoguserAvatarId() {
		return loguserAvatarId;
	}

	public void setLoguserAvatarId(String loguserAvatarId) {
		this.loguserAvatarId = loguserAvatarId;
	}

	public boolean isOverdue() {
		if (this.getDuedate() != null) {
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			Date todayDate = today.getTime();

			return todayDate.after(this.getDuedate());
		} else {
			return false;
		}
	}
}
