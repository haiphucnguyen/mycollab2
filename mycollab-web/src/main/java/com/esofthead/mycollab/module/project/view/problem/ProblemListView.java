package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface ProblemListView extends
		ListView<ProblemSearchCriteria, SimpleProblem> {

	public static final String VIEW_DEF_ID = "project-problem-list";
}
