package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.vaadin.desktop.ui.ListView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface ProblemListView extends ListView<ProblemSearchCriteria, SimpleProblem> {

	String VIEW_DEF_ID = "project-problem-list";
}
