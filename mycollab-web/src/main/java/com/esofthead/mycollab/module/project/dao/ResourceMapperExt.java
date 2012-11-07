package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.SimpleResource;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;

public interface ResourceMapperExt {
	int getTotalCount(ResourceSearchCriteria criteria);

	List<SimpleResource> findPagableList(ResourceSearchCriteria criteria,
			RowBounds rowBounds);

	List<String> getResourceNamesByProjectId(int projectId);

	void insertAndReturnKey(Resource resource);
}
