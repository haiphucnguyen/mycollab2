package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectComponentCommand {
	void componentRemoved(String username, int accountId, int projectId,
			int bugId);
}
