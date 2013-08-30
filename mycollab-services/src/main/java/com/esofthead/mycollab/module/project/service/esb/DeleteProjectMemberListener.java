package com.esofthead.mycollab.module.project.service.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface DeleteProjectMemberListener extends IService {
	void projectMemberRemoved(String username, Integer projectMemberId,
			Integer projectId, Integer accountId);
}
