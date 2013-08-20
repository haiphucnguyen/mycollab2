package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;

@RemotingDestination
public interface ProjectRoleService extends
		IDefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria> {

	@CacheEvict
	void savePermission(int projectId, int roleId, PermissionMap permissionMap,
			@CacheKey Integer sAccountId);

	@Cacheable
	SimpleProjectRole findById(int roleId, @CacheKey int sAccountId);
}
