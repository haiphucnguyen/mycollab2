package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectVersionCommand {
	void versionRemoved(String username, int accountId, int projectId, int bugId);
}
