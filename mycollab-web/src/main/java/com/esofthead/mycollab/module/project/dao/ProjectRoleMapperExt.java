package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;

public interface ProjectRoleMapperExt extends ISearchableDAO<ProjectRoleSearchCriteria>{
	SimpleProjectRole findRoleById(int roleId);
}
