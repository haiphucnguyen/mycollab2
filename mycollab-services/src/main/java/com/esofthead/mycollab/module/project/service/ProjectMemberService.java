/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.dist.NotMobile;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

/**
 * 
 * @author haiphucnguyen
 */
@RemotingDestination
public interface ProjectMemberService extends
		IDefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria> {

	@Cacheable
	SimpleProjectMember findById(int memberId, @CacheKey int sAccountId);

	@Cacheable
	SimpleProjectMember findMemberByUsername(String username, int projectId,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<SimpleUser> getUsersNotInProject(int projectId,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<SimpleUser> getActiveUsersInProject(int projectId,
			@CacheKey Integer sAccountId);

	@NotMobile
	void inviteProjectMember(String[] email, int projectId, int projectRoleId,
			String inviteUser, int sAccountId);

	@CacheEvict
	void acceptProjectInvitationByNewUser(String email, String password,
			Integer projectId, Integer projectRoleId, Integer sAccountId);
}
