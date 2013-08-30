/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapperExt;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.esb.ProjectEndPoints;
import com.esofthead.mycollab.module.project.service.esb.ProjectMemberDeleteListener;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.project.ProjectMemberInviteNotificationAction;
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
	public int saveWithSession(ProjectMember record, String username) {
		if (record.getStatus() == null) {
			record.setStatus(ProjectMemberStatusContants.VERIFICATING);
		}
		int recordId = super.saveWithSession(record, username);
		if (recordId > 0) {
			RelayEmailNotification relayNotification = new RelayEmailNotification();
			relayNotification.setChangeby(username);
			relayNotification.setChangecomment("");
			int sAccountId = record.getSaccountid();
			relayNotification.setSaccountid(sAccountId);
			relayNotification.setType("invitationMember");
			relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
			relayNotification.setTypeid(recordId);
			relayNotification.setExtratypeid(record.getProjectid());
			relayNotification
					.setEmailhandlerbean(ProjectMemberInviteNotificationAction.class
							.getName());
			relayEmailNotificationService.saveWithSession(relayNotification,
					username);
		}
		return recordId;
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
			CamelContext camelContext = ApplicationContextUtil
					.getBean(CamelContext.class);
			try {
				Project project = projectMapper
						.selectByPrimaryKey(projectMember.getProjectid());
				ProjectMemberDeleteListener projectMemberDeleteListener = new ProxyBuilder(
						camelContext).endpoint(
						ProjectEndPoints.PROJECT_MEMBER_DELETE_ENDPOINT).build(
						ProjectMemberDeleteListener.class);
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
}
