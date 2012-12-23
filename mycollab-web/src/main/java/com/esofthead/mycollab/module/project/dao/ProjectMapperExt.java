package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;

public interface ProjectMapperExt extends ISearchableDAO<ProjectSearchCriteria> {

	List<SimpleProject> getInvolvedProjectOfUser(String username);

	List<String> getUserMailsOfProject(int projectid);
}
