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
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author haiphucnguyen
 */
@Service
// @Auditable(module = ModuleNameConstants.PRJ, type =
// ProjectContants.PROJECT_MEMBER)
public class ProjectMemberServiceImpl extends
		DefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria>
		implements ProjectMemberService {
	private static Logger log = LoggerFactory
			.getLogger(ProjectMemberServiceImpl.class);

	@Autowired
	protected ProjectMemberMapper projectMemberMapper;

	@Autowired
	protected ProjectMemberMapperExt projectMemberMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return projectMemberMapper;
	}

	@Override
	public ISearchableDAO<ProjectMemberSearchCriteria> getSearchMapper() {
		return projectMemberMapperExt;
	}

	@Override
	public SimpleProjectMember findMemberById(int memberId) {
		return projectMemberMapperExt.findMemberById(memberId);
	}

	@Override
	public List<SimpleUser> getUsersNotInProject(int projectId) {
		return projectMemberMapperExt.getUsersNotInProject(projectId);
	}

	@Override
	public SimpleProjectMember findMemberByUsername(String username,
			int projectId) {
		return projectMemberMapperExt.findMemberByUsername(username, projectId);
	}

	@Override
	public int saveWithSession(ProjectMember record, String username) {
		if (record.getStatus() == null) {
			record.setStatus(ProjectMemberStatusContants.VERIFICATING);
		}
		return super.saveWithSession(record, username);
	}

	@Override
	public List<SimpleUser> getUsersInProject(int projectId) {
		return projectMemberMapperExt.getUsersInProject(projectId);
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username) {
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
						ProjectEndPoints.PROJECT_MEMBER_DELETE_ENDPOINTS)
						.build(ProjectMemberDeleteListener.class);
				projectMemberDeleteListener.projectMemberRemoved(username,
						primaryKey, projectMember.getProjectid(),
						project.getSaccountid());
			} catch (Exception e) {
				log.error("Error while notify user delete", e);
			}
		}
		return super.removeWithSession(primaryKey, username);
	}
}
