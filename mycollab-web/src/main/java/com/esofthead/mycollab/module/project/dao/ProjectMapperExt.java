package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;

public interface ProjectMapperExt {
	int getTotalCount(ProjectSearchCriteria criteria);

	List<SimpleProject> findPagableList(ProjectSearchCriteria criteria,
			RowBounds rowBounds);

	List<SimpleProject> getInvolvedProjectOfUser(String username);

	void insertAndReturnKey(Project project);

	List<String> getUserMailsOfProject(int projectid);
}
