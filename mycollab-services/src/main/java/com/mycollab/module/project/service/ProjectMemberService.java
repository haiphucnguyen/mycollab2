package com.mycollab.module.project.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.ProjectMember;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.mycollab.module.user.domain.SimpleUser;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProjectMemberService extends IDefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria> {

    @Cacheable
    SimpleProjectMember findById(Integer memberId, @CacheKey Integer sAccountId);

    @Cacheable
    boolean isUserBelongToProject(String username, Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    SimpleProjectMember findMemberByUsername(String username, Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    SimpleUser getActiveUserOfProject(String username, Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    List<SimpleUser> getUsersNotInProject(Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    List<SimpleUser> getActiveUsersInProject(Integer projectId, @CacheKey Integer sAccountId);

    @Cacheable
    List<SimpleUser> getActiveUsersInProjects(List<Integer> projectIds, @CacheKey Integer sAccountId);

    void inviteProjectMembers(String[] email, Integer projectId, Integer projectRoleId,
                              String inviteUser, String inviteMessage, Integer sAccountId);

    List<SimpleProjectMember> findMembersHourlyInProject(Integer projectId, Integer sAccountId, Date start, Date end);
}
