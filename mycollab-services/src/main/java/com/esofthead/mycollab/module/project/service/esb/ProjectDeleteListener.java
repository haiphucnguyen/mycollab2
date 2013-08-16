package com.esofthead.mycollab.module.project.service.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface ProjectDeleteListener extends IService {
	void projectRemoved(int accountId, int projectId);
}
