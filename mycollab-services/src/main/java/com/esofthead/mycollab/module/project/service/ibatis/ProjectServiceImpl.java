/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.esb.BeanProxyBuilder;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapperExt;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.esb.DeleteProjectCommand;
import com.esofthead.mycollab.module.project.esb.ProjectEndPoints;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.AccessPermissionFlag;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "name", type = ProjectContants.PROJECT, extraFieldName = "id")
public class ProjectServiceImpl extends
		DefaultService<Integer, Project, ProjectSearchCriteria> implements
		ProjectService {

	private static Logger log = LoggerFactory
			.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectMapperExt projectMapperExt;

	@Autowired
	private ProjectMemberMapper projectMemberMapper;

	@Autowired
	private ProjectRoleService projectRoleService;

	@Override
	public ICrudGenericDAO<Integer, Project> getCrudMapper() {
		return projectMapper;
	}

	@Override
	public ISearchableDAO<ProjectSearchCriteria> getSearchMapper() {
		return projectMapperExt;
	}

	@Override
	public int saveWithSession(Project record, String username) {
		int projectid = super.saveWithSession(record, username);

		// Add the first user to project
		ProjectMember projectMember = new ProjectMember();
		projectMember.setIsadmin(Boolean.TRUE);
		projectMember.setStatus(ProjectMemberStatusConstants.ACTIVE);
		projectMember.setJoindate(new GregorianCalendar().getTime());
		projectMember.setProjectid(projectid);
		projectMember.setUsername(username);
		projectMember.setSaccountid(record.getSaccountid());
		projectMemberMapper.insert(projectMember);

		// add client role to project
		ProjectRole clientRole = createProjectRole(projectid,
				record.getSaccountid(), "Client", "Default role for client");
		int clientRoleId = projectRoleService.saveWithSession(clientRole,
				username);

		PermissionMap permissionMapClient = new PermissionMap();
		for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {

			String permissionName = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];

			if (permissionName.equals(ProjectRolePermissionCollections.USERS)
					|| permissionName
							.equals(ProjectRolePermissionCollections.ROLES)
					|| permissionName
							.equals(ProjectRolePermissionCollections.MESSAGES)) {
				permissionMapClient
						.addPath(
								ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i],
								AccessPermissionFlag.NO_ACCESS);
			} else {
				permissionMapClient
						.addPath(
								ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i],
								AccessPermissionFlag.READ_ONLY);
			}
		}
		projectRoleService.savePermission(projectid, clientRoleId,
				permissionMapClient, record.getSaccountid());

		// add consultant role to project
		log.debug("Add consultant role to project {}", record.getName());
		ProjectRole consultantRole = createProjectRole(projectid,
				record.getSaccountid(), "Consultant",
				"Default role for consultant");
		int consultantRoleId = projectRoleService.saveWithSession(
				consultantRole, username);

		PermissionMap permissionMapConsultant = new PermissionMap();
		for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {

			String permissionName = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];

			if (permissionName.equals(ProjectRolePermissionCollections.USERS)
					|| permissionName
							.equals(ProjectRolePermissionCollections.ROLES)) {
				permissionMapConsultant
						.addPath(
								ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i],
								AccessPermissionFlag.READ_ONLY);
			} else {
				permissionMapConsultant
						.addPath(
								ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i],
								AccessPermissionFlag.ACCESS);
			}
		}
		projectRoleService.savePermission(projectid, consultantRoleId,
				permissionMapConsultant, record.getSaccountid());

		// add admin role to project
		log.debug("Add admin role to project {}", record.getName());
		ProjectRole adminRole = createProjectRole(projectid,
				record.getSaccountid(), "Admin", "Default role for admin");
		int adminRoleId = projectRoleService.saveWithSession(adminRole,
				username);

		PermissionMap permissionMapAdmin = new PermissionMap();
		for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {

			permissionMapAdmin.addPath(
					ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i],
					AccessPermissionFlag.ACCESS);
		}
		projectRoleService.savePermission(projectid, adminRoleId,
				permissionMapAdmin, record.getSaccountid());

		// set default permission

		return projectid;
	}

	private ProjectRole createProjectRole(int projectId, int sAccountId,
			String roleName, String description) {
		ProjectRole projectRole = new ProjectRole();
		projectRole.setProjectid(projectId);
		projectRole.setSaccountid(sAccountId);
		projectRole.setRolename(roleName);
		projectRole.setDescription(description);
		return projectRole;
	}

	@Override
	public List<ProjectActivityStream> getProjectActivityStreams(
			SearchRequest<ActivityStreamSearchCriteria> searchRequest) {
		return projectMapperExt.getProjectActivityStreams(
				searchRequest.getSearchCriteria(),
				new RowBounds((searchRequest.getCurrentPage() - 1)
						* searchRequest.getNumberOfItems(), searchRequest
						.getNumberOfItems()));
	}

	@Override
	public int getTotalActivityStream(
			ActivityStreamSearchCriteria searchCriteria) {
		return projectMapperExt.getTotalActivityStream(searchCriteria);
	}

	@Override
	public SimpleProject findById(int projectId, int sAccountId) {
		return projectMapperExt.findProjectById(projectId);
	}

	@Override
	public List<Integer> getUserProjectKeys(String username, Integer sAccountId) {
		ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
		searchCriteria.setInvolvedMember(new StringSearchField(username));
		return projectMapperExt.getUserProjectKeys(searchCriteria);
	}

	@Override
	public String getSubdomainOfProject(int projectId) {
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			return projectMapperExt.getSubdomainOfProject(projectId);
		} else {
			return SiteConfiguration.getSiteUrl("");
		}
	}

	@Override
	public int removeWithSession(Integer projectId, String username,
			int accountId) {
		// notify listener project is removed, then silently remove project in
		// associate records
		try {
			Project project = findByPrimaryKey(projectId, accountId);

			DeleteProjectCommand projectDeleteListener = new BeanProxyBuilder()
					.build(ProjectEndPoints.PROJECT_REMOVE_ENDPOINT,
							DeleteProjectCommand.class);
			projectDeleteListener.projectRemoved(project.getSaccountid(),
					projectId);
		} catch (Exception e) {
			log.error("Error while notify user delete", e);
		}
		return super.removeWithSession(projectId, username, accountId);
	}
}
