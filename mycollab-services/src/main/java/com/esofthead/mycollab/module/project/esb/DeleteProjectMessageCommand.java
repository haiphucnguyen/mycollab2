package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectMessageCommand {
	void messageRemoved(String username, int accountId, int projectId, int bugId);
}
