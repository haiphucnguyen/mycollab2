package com.esofthead.mycollab.module.project.service.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface DeleteProjectListener extends IService {
	void projectRemoved(int accountId, int projectId);
}
