package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.project.domain.Permission;

public interface PermissionService extends ICrudService<Integer, Permission> {
	
	List<Permission> getByProjectId(int projectId);

	
	List<Permission> getPermissionsofUserOnProject(int projectId,
			String username);

	void savePermissions(int projectId, List<Permission> permissions);
}
