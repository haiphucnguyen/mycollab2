package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectTaskListCommand {
	void taskListRemoved(String username, int accountId, int projectId,
			int taskListId);
}
