/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectMemberMapperExt extends
		ISearchableDAO<ProjectMemberSearchCriteria> {
	SimpleProjectMember findMemberById(int memberId);

	List<SimpleUser> getUsersNotInProject(@Param("projectId") int projectId,
			@Param("sAccountId") int sAccountId);

	List<SimpleUser> getUsersInProject(@Param("projectId") int projectId,
			@Param("sAccountId") int sAccountId);

	List<SimpleUser> getActiveUsersInProject(@Param("projectId") int projectId,
			@Param("sAccountId") int sAccountId);

	SimpleProjectMember findMemberByUsername(
			@Param("username") String username,
			@Param("projectId") int projectId);
}
