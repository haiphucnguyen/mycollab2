package com.esofthead.mycollab.module.project.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface DeleteProjectListener extends IService {
	void projectRemoved(int accountId, int projectId);
}
