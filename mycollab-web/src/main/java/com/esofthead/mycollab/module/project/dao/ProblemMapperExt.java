package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;

public interface ProblemMapperExt extends ISearchableDAO<ProblemSearchCriteria>{

	void insertAndReturnKey(Problem problem);
}
