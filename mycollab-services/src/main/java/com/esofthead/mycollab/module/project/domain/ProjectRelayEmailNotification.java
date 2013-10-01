package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;

public class ProjectRelayEmailNotification extends SimpleRelayEmailNotification {
	private static final long serialVersionUID = 1L;

	private int projectId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
