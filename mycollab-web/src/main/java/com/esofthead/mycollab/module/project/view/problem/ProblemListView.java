package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface ProblemListView extends View {
	
	public static final String VIEW_DEF_ID = "project-problem-list";

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<ProblemSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<SimpleProblem> getSelectableItemHandlers();

    IPagedBeanTable<ProblemSearchCriteria, SimpleProblem> getPagedBeanTable();
}
