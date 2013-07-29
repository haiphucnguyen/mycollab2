package com.esofthead.mycollab.module.project.service.esb;

public interface ProjectBugDeleteListener {
	void bugRemoved(String username, int accountId, int projectId, int bugId);
}
