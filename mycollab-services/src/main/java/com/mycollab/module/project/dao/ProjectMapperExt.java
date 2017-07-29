package com.mycollab.module.project.dao;

import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.FollowingTicket;
import com.mycollab.module.project.domain.ProjectActivityStream;
import com.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.user.domain.BillingAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface ProjectMapperExt extends ISearchableDAO<ProjectSearchCriteria> {

    int getTotalActivityStream(@Param("searchCriteria") ActivityStreamSearchCriteria criteria);

    List<ProjectActivityStream> getProjectActivityStreams(@Param("searchCriteria") ActivityStreamSearchCriteria criteria, RowBounds rowBounds);

    List<Integer> getUserProjectKeys(@Param("searchCriteria") ProjectSearchCriteria criteria);

    List<SimpleProject> getProjectsUserInvolved(@Param("username") String username, @Param("sAccountId") Integer sAccountId);

    SimpleProject findProjectById(int projectId);

    BillingAccount getAccountInfoOfProject(int projectId);

    int getTotalFollowingTickets(@Param("searchCriteria") MonitorSearchCriteria searchRequest);

    List<FollowingTicket> getProjectFollowingTickets(@Param("searchCriteria") MonitorSearchCriteria searchRequest, RowBounds rowBounds);

    List<ProjectRelayEmailNotification> findProjectRelayEmailNotifications();
}
