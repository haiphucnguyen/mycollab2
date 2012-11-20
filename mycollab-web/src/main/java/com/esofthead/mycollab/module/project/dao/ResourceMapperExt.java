package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;

public interface ResourceMapperExt extends ISearchableDAO<ResourceSearchCriteria>{

	List<String> getResourceNamesByProjectId(int projectId);

	void insertAndReturnKey(Resource resource);
}
