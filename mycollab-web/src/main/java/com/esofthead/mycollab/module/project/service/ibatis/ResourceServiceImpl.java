package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.PermissionPaths;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.dao.ResourceMapper;
import com.esofthead.mycollab.module.project.dao.ResourceMapperExt;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;
import com.esofthead.mycollab.module.project.service.PermissionService;
import com.esofthead.mycollab.module.project.service.ResourceService;

@Service
public class ResourceServiceImpl extends
		DefaultService<Integer, Resource, ResourceSearchCriteria> implements
		ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private ResourceMapperExt resourceMapperExt;

	@Autowired
	private PermissionService permissionService;

	@Override
	public ICrudGenericDAO<Integer, Resource> getCrudMapper() {
		return resourceMapper;
	}

	@Override
	public ISearchableDAO<ResourceSearchCriteria> getSearchMapper() {
		return resourceMapperExt;
	}

	@Override
	public List<String> getResourceNamesByProjectId(int projectId) {
		return resourceMapperExt.getResourceNamesByProjectId(projectId);
	}

	@Override
	public void saveOrUpdateResource(Resource resource, String userSessionId) {

		if (resource.getId() == null) {
			saveWithSession(resource, userSessionId);
		} else {
			Resource extResource = resourceMapper.selectByPrimaryKey(resource
					.getId());
			if (extResource != null) {
				if (extResource.getUsername() != resource.getResourcename()) {
					updateWithSession(resource, userSessionId);
					addDefaultPermissions(resource);

					// remove permission of previous user
					PermissionExample perEx = new PermissionExample();
					perEx.createCriteria()
							.andProjectidEqualTo(resource.getProjectid())
							.andUsernameEqualTo(extResource.getUsername());
					((PermissionMapper) ((PermissionServiceImpl) permissionService)
							.getCrudMapper()).deleteByExample(perEx);
				} else {
					updateWithSession(resource, userSessionId);
				}
			}
		}
	}

	private void addDefaultPermissions(Resource resource) {
		int projectid = resource.getProjectid();
		String username = resource.getUsername();

		if (username != null) {
			String[] defaultAccessPathIds = { PermissionPaths.VIEW_MEMBERS,
					PermissionPaths.VIEW_MESSAGES,
					PermissionPaths.VIEW_DEFECTS,
					PermissionPaths.VIEW_PROBLEMS, PermissionPaths.VIEW_RISKS,
					PermissionPaths.VIEW_TASKS };

			for (String permissionPath : defaultAccessPathIds) {
				Permission permission = new Permission();
				permission.setPathid(permissionPath);
				permission.setProjectid(projectid);
				permission.setUsername(username);
				permission.setCanaccess(true);
				permissionService.saveWithSession(permission, null);
			}
		}
	}
}
