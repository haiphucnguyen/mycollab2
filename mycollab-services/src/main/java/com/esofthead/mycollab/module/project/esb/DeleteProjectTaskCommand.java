package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectTaskCommand {
	void taskRemoved(String username, int accountId, int projectId, int taskId);
}
