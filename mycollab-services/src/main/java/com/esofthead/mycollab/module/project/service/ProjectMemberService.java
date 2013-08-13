/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
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

	@Cacheable
	SimpleProjectMember findById(int memberId, @CacheKey Integer sAccountId);

	@Cacheable
	SimpleProjectMember findMemberByUsername(String username, int projectId,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<SimpleUser> getUsersNotInProject(int projectId,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<SimpleUser> getUsersInProject(int projectId,
			@CacheKey Integer sAccountId);
}
