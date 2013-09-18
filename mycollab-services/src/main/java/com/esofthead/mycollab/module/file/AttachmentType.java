package com.esofthead.mycollab.module.file;

public enum AttachmentType {
	CRM_NOTE_TYPE("crm-note"), PROJECT_MESSAGE("project-message"), PROJECT_BUG_TYPE(
			"project-bug"), PROJECT_MILESTONE("project-milestone"), PROJECT_RISK(
			"project-risk"), PROJECT_PROBLEM("project-problem"), PROJECT_TASKLIST(
			"project-tasklist"), PROJECT_TASK_TYPE("project-task"), COMMON_COMMENT(
			"common-comment");

	private String type;

	AttachmentType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}
