package com.esofthead.mycollab.module.tracker.domain;

import java.util.List;

public class SimpleBug extends Bug {
	
	private String loguserFullName;

	private String assignuserFullName;
	
	private String projectname;

	private List<Version> affectedVersions;

	private List<Version> fixedVersions;

	private List<Component> components;

	private List<String> attachmentPaths;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public List<String> getAttachmentPaths() {
		return attachmentPaths;
	}

	public void setAttachmentPaths(List<String> attachmentPaths) {
		this.attachmentPaths = attachmentPaths;
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
}
