package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectBugCommand {
	void bugRemoved(String username, int accountId, int projectId, int bugId);
}
