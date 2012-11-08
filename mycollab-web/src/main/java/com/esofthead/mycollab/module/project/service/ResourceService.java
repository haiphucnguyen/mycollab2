package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;

public interface ResourceService extends
		IDefaultService<Integer, Resource, ResourceSearchCriteria> {
	void saveOrUpdateResource(Resource resource, String userSessionId);

	List<String> getResourceNamesByProjectId(int projectId);
}
