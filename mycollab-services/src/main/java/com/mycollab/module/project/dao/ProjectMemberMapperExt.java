package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.mycollab.module.user.domain.SimpleUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProjectMemberMapperExt extends ISearchableDAO<ProjectMemberSearchCriteria> {
    SimpleProjectMember findMemberById(int memberId);

    List<SimpleUser> getUsersNotInProject(@Param("projectId") int projectId, @Param("sAccountId") int sAccountId);

    List<SimpleUser> getActiveUsersInProject(@Param("projectId") int projectId, @Param("sAccountId") int sAccountId);

    SimpleProjectMember findMemberByUsername(@Param("username") String username, @Param("projectId") int projectId);

    List<SimpleUser> getActiveUsersInProjects(@Param("projectIds") List<Integer> projectIds, @Param("sAccountId") Integer sAccountId);

    SimpleUser getActiveUserOfProject(@Param("username") String username, @Param("projectId") Integer projectId,
                                      @Param("sAccountId") Integer sAccountId);

    List<SimpleProjectMember> findMembersHourlyInProject(@Param("projectId") Integer projectId, @Param("sAccountId") Integer sAccountId,
                                                         @Param("start") Date start, @Param("end") Date end);
}
