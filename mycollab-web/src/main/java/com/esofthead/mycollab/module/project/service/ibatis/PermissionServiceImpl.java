package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.PermissionExample.Criteria;
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

	@Override
	public List<Permission> getByProjectId(int projectId) {
		PermissionExample ex = new PermissionExample();
		Criteria criteria = ex.createCriteria().andProjectidEqualTo(projectId);
		return permissionMapper.selectByExample(ex);
	}

	@Override
	public List<Permission> getPermissionsofUserOnProject(int projectId,
			String username) {
		PermissionExample ex = new PermissionExample();
		Criteria criteria = ex.createCriteria().andProjectidEqualTo(projectId)
				.andUsernameEqualTo(username);
		return permissionMapper.selectByExample(ex);
	}

	@Override
	public void savePermissions(int projectId, List<Permission> permissions) {
		PermissionExample ex = new PermissionExample();
		ex.createCriteria().andProjectidEqualTo(projectId);
		permissionMapper.deleteByExample(ex);

		for (Permission permission : permissions) {
			permissionMapper.insert(permission);
		}
	}

}
