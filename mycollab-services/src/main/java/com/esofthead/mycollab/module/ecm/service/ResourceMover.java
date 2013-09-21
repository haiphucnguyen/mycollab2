package com.esofthead.mycollab.module.ecm.service;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ResourceMover extends IService {
	void moveResource(Resource scrRes, Resource descRes, String userMove, Integer sAccountId);
}
