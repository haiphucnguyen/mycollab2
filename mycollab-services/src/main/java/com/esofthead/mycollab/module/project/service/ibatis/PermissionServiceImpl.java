package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.service.PermissionService;

@Service
public class PermissionServiceImpl extends
		DefaultCrudService<Integer, Permission> implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public ICrudGenericDAO<Integer, Permission> getCrudMapper() {
		return permissionMapper;
	}

}
