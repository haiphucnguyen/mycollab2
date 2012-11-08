/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.AvailableDestinationNames;
import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.core.MessageDispatcher;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.PermissionPaths;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapperExt;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectExample;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;

public class ProjectServiceImpl extends DefaultCrudService<Integer, Project>
		implements ProjectService {

	private ProjectMapperExt projectExtDAO;

	private PermissionMapper permissionDAO;

	private MessageDispatcher messageDispatcher;

	public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
		this.messageDispatcher = messageDispatcher;
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Project project = super.findByPrimaryKey(primaryKey);
		if (messageDispatcher != null && project != null) {
			Dictionary<String, Object> props = new Hashtable<String, Object>();
			props.put("project", project);
			messageDispatcher.dispatchObject(
					AvailableDestinationNames.PROJECT_REMOVE, props);
		}
		return super.internalRemoveWithSession(primaryKey, username);
	}

	public void setProjectExtDAO(ProjectMapperExt projectExtDAO) {
		this.projectExtDAO = projectExtDAO;
	}

	@Override
	public List findPagableListByCriteria(ProjectSearchCriteria criteria,
			int skipNum, int maxResult) {
		return projectExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ProjectSearchCriteria criteria) {
		return projectExtDAO.getTotalCount(criteria);
	}

	@Override
	protected int internalUpdateWithSession(Project record, String username) {
		super.internalUpdateWithSession(record, username);
		PermissionExample ex = new PermissionExample();
		if (record.getOwner() != null && record.getOwner() != "") {
			ex.createCriteria().andProjectidEqualTo(record.getId())
					.andUsernameEqualTo(record.getOwner());
			permissionDAO.deleteByExample(ex);
			setAllPermissions(record.getId(), record.getOwner());
		}

		return 0;
	}

	@Override
	protected void internalSaveWithSession(Project record, String username) {
		if (isExistProjectHasSameName(record.getName())) {
			throw new EngroupException("There is project has name "
					+ record.getName()
					+ " already. Please choose another project name");
		}

		projectExtDAO.insertAndReturnKey(record);
		int projectid = record.getId();

		if (record.getOwner() != null && record.getOwner() != "") {
			// set all project permissions to project owner
			setAllPermissions(projectid, record.getOwner());
		}

		if (messageDispatcher != null) {
			Dictionary<String, Object> props = new Hashtable<String, Object>();
			props.put("project", record);
			messageDispatcher.dispatchObject(
					AvailableDestinationNames.PROJECT_ADD, props);
		}
	}

	private void setAllPermissions(int projectid, String user) {
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_MEMBERS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_MESSAGES));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_PERMISSIONS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_PROBLEMS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_RISKS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.EDIT_TASKS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_MEMBERS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_MESSAGES));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_PERMISSIONS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_PROBLEMS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_RISKS));
		permissionDAO.insert(createPermission(projectid, user,
				PermissionPaths.VIEW_TASKS));
	}

	private Permission createPermission(int projectid, String user,
			String permissionPath) {
		Permission permission = new Permission();
		permission.setCanaccess(true);
		permission.setPathid(permissionPath);
		permission.setProjectid(projectid);
		permission.setUsername(user);
		return permission;
	}

	@Override
	public boolean isExistProjectHasSameName(String name) {
		ProjectExample ex = new ProjectExample();
		ex.createCriteria().andNameEqualTo(name);
		List<Project> list = ((ProjectMapper) daoObj).selectByExample(ex);
		return (list != null && list.size() > 0);
	}

	@Override
	public List<SimpleProject> getInvolvedProjectOfUser(String username) {
		return projectExtDAO.getInvolvedProjectOfUser(username);
	}

	public void setPermissionDAO(PermissionMapper permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	

}
