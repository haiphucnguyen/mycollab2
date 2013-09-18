package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectMilestoneCommand {
	void milestoneRemoved(String username, int accountId, int projectId,
			int bugId);
}
