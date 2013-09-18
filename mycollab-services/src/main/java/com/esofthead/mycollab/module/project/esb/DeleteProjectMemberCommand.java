package com.esofthead.mycollab.module.project.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface DeleteProjectMemberCommand extends IService {
	void projectMemberRemoved(String username, Integer projectMemberId,
			Integer projectId, Integer accountId);
}
