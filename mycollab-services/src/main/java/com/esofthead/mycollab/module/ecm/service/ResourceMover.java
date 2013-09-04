package com.esofthead.mycollab.module.ecm.service;

import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ResourceMover {
	void moveResource(Resource scrRes, Resource descRes);
}
