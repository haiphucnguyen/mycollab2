package com.esofthead.mycollab.common;

public enum CommentType {
	PRJ_TASK_LIST("Project-TaskList"), PRJ_TASK("Project-Task"), PRJ_BUG(
			"Project-Bug"), PRJ_MESSAGE("Project-Message"), PRJ_MILESTONE(
			"Project-Milestone"), PRJ_RISK("Project-Risk"), PRJ_PROBLEM(
			"Project-Problem"), CRM_NOTE("Crm-Note");

	private String type;

	CommentType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
