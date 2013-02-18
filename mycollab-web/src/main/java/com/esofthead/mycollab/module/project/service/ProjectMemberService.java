/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectMemberService extends
		IDefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria> {
	SimpleProjectMember findMemberById(int memberId);

	List<SimpleUser> getUsersNotInProject(int projectId);
}
