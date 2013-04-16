/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapperExt;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * 
 * @author haiphucnguyen
 */
@Service
//@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.PROJECT_MEMBER)
public class ProjectMemberServiceImpl extends
		DefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria>
		implements ProjectMemberService {

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

}
