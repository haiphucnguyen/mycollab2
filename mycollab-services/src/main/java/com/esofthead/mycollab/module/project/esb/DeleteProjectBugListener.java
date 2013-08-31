package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectBugListener {
	void bugRemoved(String username, int accountId, int projectId, int bugId);
}
