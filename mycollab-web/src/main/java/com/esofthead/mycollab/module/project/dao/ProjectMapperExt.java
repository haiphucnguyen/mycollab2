package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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
}
