package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;

public interface ProjectMapperExt extends ISearchableDAO<ProjectSearchCriteria> {

	int getTotalActivityStream(
			@Param("searchCriteria") ActivityStreamSearchCriteria criteria);

	List<ProjectActivityStream> getProjectActivityStreams(
			@Param("searchCriteria") ActivityStreamSearchCriteria criteria,
			RowBounds rowBounds);

	List<Integer> getUserProjectKeys(
			@Param("searchCriteria") ProjectSearchCriteria criteria);

	SimpleProject findProjectById(int projectId);

	String getSubdomainOfProject(int projectId);

	int getTotalFollowingTickets(
			@Param("searchCriteria") MonitorSearchCriteria searchRequest);

	List<FollowingTicket> getProjectFollowingTickets(
			@Param("searchCriteria") MonitorSearchCriteria searchRequest,
			RowBounds rowBounds);
	
	List<ProjectRelayEmailNotification> findProjectRelayEmailNotifications();
}
