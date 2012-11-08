package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.PermissionPaths;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.dao.ResourceMapperExt;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.PermissionService;
import com.esofthead.mycollab.module.project.service.ResourceService;

public class ResourceServiceImpl extends DefaultCrudService<Integer, Resource>
		implements ResourceService {

	private ResourceMapperExt resourceExtDAO;

	private PermissionService permissionService;

	private ChangeLogService changeLogService;

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setChangeLogService(ChangeLogService changeLogService) {
		this.changeLogService = changeLogService;
	}

	@Override
	public List findPagableListByCriteria(ResourceSearchCriteria criteria,
			int skipNum, int maxResult) {
		return resourceExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ResourceSearchCriteria criteria) {
		return resourceExtDAO.getTotalCount(criteria);
	}

	public void setResourceExtDAO(ResourceMapperExt resourceExtDAO) {
		this.resourceExtDAO = resourceExtDAO;
	}

	@Override
	public List<String> getResourceNamesByProjectId(int projectId) {
		return resourceExtDAO.getResourceNamesByProjectId(projectId);
	}

	@Override
	protected void internalSaveWithSession(Resource record, String username) {
		resourceExtDAO.insertAndReturnKey(record);
		int resourceid = record.getId();
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.RESOURCE, resourceid, ChangeLogAction.CREATE,
				record.getResourcename());
		addDefaultPermissions(record);
	}

	@Override
	protected int internalUpdateWithSession(Resource record, String username) {
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.RESOURCE, record.getId(),
				ChangeLogAction.UPDATE, record.getResourcename());
		return super.internalUpdateWithSession(record, username);
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Resource record = findByPrimaryKey(primaryKey);
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.RESOURCE, record.getId(),
				ChangeLogAction.DELETE, record.getResourcename());
		return super.internalRemoveWithSession(primaryKey, username);
	}

	@Override
	public void saveOrUpdateResource(Resource resource, String userSessionId) {

		if (resource.getId() == null) {
			saveWithSession(resource, userSessionId);
		} else {
			Resource extResource = daoObj.selectByPrimaryKey(resource.getId());
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
							.getDaoObj()).deleteByExample(perEx);
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
