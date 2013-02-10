package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;

public interface ProjectRoleService extends
		IDefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria> {

	void savePermission(int roleId, PermissionMap permissionMap);

	SimpleProjectRole findRoleById(int roleId);
}
