package com.esofthead.mycollab.module.project.service.esb;

public interface ProjectMemberDeleteListener {
	void projectMemberRemoved(String username, Integer projectMemberId,
			Integer projectId, Integer accountId);
}
