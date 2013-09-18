package com.esofthead.mycollab.module.project.esb;

public interface DeleteProjectRiskCommand {
	void riskRemoved(String username, int accountId, int projectId, int bugId);
}
