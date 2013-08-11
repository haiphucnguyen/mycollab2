package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.ProjectRoleMapper;
import com.esofthead.mycollab.module.project.dao.ProjectRoleMapperExt;
import com.esofthead.mycollab.module.project.dao.ProjectRolePermissionMapper;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermission;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermissionExample;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Service
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.PROJECT_ROLE)
public class ProjectRoleServiceImpl extends
		DefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria>
		implements ProjectRoleService {

	@Autowired
	private ProjectRoleMapper roleMapper;
	
	@Autowired
	private ProjectRoleMapperExt roleMapperExt;

	@Autowired
	private ProjectRolePermissionMapper projectRolePermissionMapper;

	@Override
	public ICrudGenericDAO<Integer, ProjectRole> getCrudMapper() {
		return roleMapper;
	}

	@Override
	public ISearchableDAO<ProjectRoleSearchCriteria> getSearchMapper() {
		return roleMapperExt;
	}

	@Override
	public void savePermission(int projectId, int roleId, PermissionMap permissionMap) {
		XStream xstream = new XStream(new StaxDriver());
		String perVal = xstream.toXML(permissionMap);

		ProjectRolePermissionExample ex = new ProjectRolePermissionExample();
		ex.createCriteria().andRoleidEqualTo(roleId);

		ProjectRolePermission rolePer = new ProjectRolePermission();
		rolePer.setRoleid(roleId);
		rolePer.setProjectid(projectId);
		rolePer.setRoleval(perVal);

		int data = projectRolePermissionMapper.countByExample(ex);
		if (data > 0) {
			projectRolePermissionMapper.updateByExampleSelective(rolePer, ex);
		} else {
			projectRolePermissionMapper.insert(rolePer);
		}

	}

	@Override
	public SimpleProjectRole findById(int roleId) {
		return roleMapperExt.findRoleById(roleId);
	}
}
