package com.esofthead.mycollab.module.project.service.esb;

public interface DeleteProjectBugListener {
	void bugRemoved(String username, int accountId, int projectId, int bugId);
}
