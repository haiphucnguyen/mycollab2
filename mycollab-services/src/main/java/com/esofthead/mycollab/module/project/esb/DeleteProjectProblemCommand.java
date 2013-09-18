package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectProblemCommand {
	void problemRemoved(String username, int accountId, int projectId, int bugId);
}
