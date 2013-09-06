/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.esb.BeanProxyBuilder;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapperExt;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.esb.DeleteProjectMemberListener;
import com.esofthead.mycollab.module.project.esb.InviteOutsideProjectMemberListener;
import com.esofthead.mycollab.module.project.esb.ProjectEndPoints;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class ProjectMemberServiceImpl extends
		DefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria>
		implements ProjectMemberService {

	private static Logger log = LoggerFactory
			.getLogger(ProjectMemberServiceImpl.class);

	@Autowired
	protected ProjectMemberMapper projectMemberMapper;

	@Autowired
	protected ProjectMemberMapperExt projectMemberMapperExt;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return projectMemberMapper;
	}

	@Override
	public ISearchableDAO<ProjectMemberSearchCriteria> getSearchMapper() {
		return projectMemberMapperExt;
	}

	@Override
	public SimpleProjectMember findById(int memberId, int sAccountId) {
		return projectMemberMapperExt.findMemberById(memberId);
	}

	@Override
	public List<SimpleUser> getUsersNotInProject(int projectId,
			Integer sAccountId) {
		return projectMemberMapperExt.getUsersNotInProject(projectId,
				sAccountId);
	}

	@Override
	public SimpleProjectMember findMemberByUsername(String username,
			int projectId, Integer sAccountId) {
		return projectMemberMapperExt.findMemberByUsername(username, projectId);
	}

	@Override
	public List<SimpleUser> getUsersInProject(int projectId, Integer sAccountId) {
		return projectMemberMapperExt.getUsersInProject(projectId, sAccountId);
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username,
			int accountId) {
		SimpleProjectMember projectMember = projectMemberMapperExt
				.findMemberById(primaryKey);
		ProjectMapper projectMapper = ApplicationContextUtil
				.getBean(ProjectMapper.class);

		if (projectMember != null) {
			try {
				Project project = projectMapper
						.selectByPrimaryKey(projectMember.getProjectid());
				DeleteProjectMemberListener projectMemberDeleteListener = new BeanProxyBuilder()
						.build(ProjectEndPoints.PROJECT_MEMBER_DELETE_ENDPOINT,
								DeleteProjectMemberListener.class);
				projectMemberDeleteListener.projectMemberRemoved(username,
						primaryKey, projectMember.getProjectid(),
						project.getSaccountid());
			} catch (Exception e) {
				log.error("Error while notify user delete", e);
			}
		}
		return super.removeWithSession(primaryKey, username, accountId);
	}

	@Override
	public List<SimpleUser> getActiveUsersInProject(int projectId,
			Integer sAccountId) {
		return projectMemberMapperExt.getActiveUsersInProject(projectId,
				sAccountId);
	}

	@Override
	public void inviteProjectMember(String[] email, int projectId,
			int projectRoleId, String inviteUser, int sAccountId) {
		InviteOutsideProjectMemberListener listener = new BeanProxyBuilder()
				.build(ProjectEndPoints.PROJECT_SEND_INVITATION_USER,
						InviteOutsideProjectMemberListener.class);
		listener.inviteUsers(email, projectId, projectRoleId, inviteUser,
				sAccountId);
	}
}
