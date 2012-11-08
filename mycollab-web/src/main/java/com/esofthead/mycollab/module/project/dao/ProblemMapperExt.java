package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;

public interface ProblemMapperExt {
	int getTotalCount(ProblemSearchCriteria criteria);

	List<SimpleProblem> findPagableList(ProblemSearchCriteria criteria,
			RowBounds rowBounds);

	void insertAndReturnKey(Problem problem);
}
