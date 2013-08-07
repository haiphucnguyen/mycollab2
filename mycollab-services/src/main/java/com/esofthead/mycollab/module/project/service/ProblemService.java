package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;

public interface ProblemService extends
		IDefaultService<Integer, Problem, ProblemSearchCriteria> {

	SimpleProblem findById(Integer problemId);
}
