package com.esofthead.mycollab.module.project;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.dao.ProjectRolePermissionMapper;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermission;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermissionExample;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.web.AppContext;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class CurrentProjectVariables {
	private static Logger log = LoggerFactory
			.getLogger(CurrentProjectVariables.class);

	public static SimpleProject getProject() {
		return (SimpleProject) AppContext
				.getVariable(ProjectContants.CURRENT_PROJECT);
	}

	public static void setProject(SimpleProject project) {
		AppContext.putVariable(ProjectContants.CURRENT_PROJECT, project);

		// get member permission
		ProjectMemberService prjMemberService = AppContext
				.getSpringBean(ProjectMemberService.class);
		SimpleProjectMember prjMember = prjMemberService.findMemberByUsername(
				AppContext.getUsername(), project.getId(),
				AppContext.getAccountId());
		if (prjMember != null) {
			if (((prjMember.getIsadmin() != null && prjMember.getIsadmin() == Boolean.FALSE) || (prjMember
					.getIsadmin() == null))
					&& prjMember.getProjectroleid() != null) {
				ProjectRolePermissionExample ex = new ProjectRolePermissionExample();
				ex.createCriteria()
						.andRoleidEqualTo(prjMember.getProjectroleid())
						.andProjectidEqualTo(
								CurrentProjectVariables.getProjectId());
				ProjectRolePermissionMapper rolePermissionMapper = AppContext
						.getSpringBean(ProjectRolePermissionMapper.class);
				List<ProjectRolePermission> rolePermissions = rolePermissionMapper
						.selectByExampleWithBLOBs(ex);
				if (!rolePermissions.isEmpty()) {
					ProjectRolePermission rolePer = rolePermissions.get(0);
					XStream xstream = new XStream(new StaxDriver());
					PermissionMap permissionMap = (PermissionMap) xstream
							.fromXML(rolePer.getRoleval());
					prjMember.setPermissionMaps(permissionMap);
				}
			}

			AppContext.putVariable(ProjectContants.PROJECT_MEMBER, prjMember);
		} else if (!AppContext.isAdmin()) {
			throw new MyCollabException("You are not belong to this project");
		}
	}

	private static SimpleProjectMember getProjectMember() {
		return (SimpleProjectMember) AppContext
				.getVariable(ProjectContants.PROJECT_MEMBER);
	}

	private static boolean isAdmin() {
		if (AppContext.isAdmin()) {
			return true;
		}
		ProjectMember member = (ProjectMember) AppContext
				.getVariable(ProjectContants.PROJECT_MEMBER);
		if (member != null && member.getIsadmin() != null) {
			return member.getIsadmin();
		}
		return false;
	}

	public static boolean canRead(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		try {
			PermissionMap permissionMap = getProjectMember()
					.getPermissionMaps();
			if (permissionMap == null) {
				return false;
			} else {
				return permissionMap.canRead(permissionItem);
			}
		} catch (Exception e) {
			log.error("Error while checking permission", e);
			return false;
		}
	}

	public static boolean canWrite(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		try {
			PermissionMap permissionMap = getProjectMember()
					.getPermissionMaps();
			if (permissionMap == null) {
				return false;
			} else {
				return permissionMap.canWrite(permissionItem);
			}
		} catch (Exception e) {
			log.error("Error while checking permission", e);
			return false;
		}
	}

	public static boolean canAccess(String permissionItem) {
		if (isAdmin()) {
			return true;
		}

		try {
			PermissionMap permissionMap = getProjectMember()
					.getPermissionMaps();
			if (permissionMap == null) {
				return false;
			} else {
				return permissionMap.canAccess(permissionItem);
			}
		} catch (Exception e) {
			log.error("Error while checking permission", e);
			return false;
		}
	}

	public static int getProjectId() {
		SimpleProject project = getProject();
		if (project != null) {
			return project.getId();
		} else {
			return -1;
		}

	}
}
