package com.esofthead.mycollab.module.project.esb;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface DeleteProjectCommand extends IService {
	void projectRemoved(int accountId, int projectId);
}
