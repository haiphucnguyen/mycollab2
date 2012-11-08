package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;

public interface ProblemService extends
		IDefaultService<Integer, Problem, ProblemSearchCriteria> {

}
