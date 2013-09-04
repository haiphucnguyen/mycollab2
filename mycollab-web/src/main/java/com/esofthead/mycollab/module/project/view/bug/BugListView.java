package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface BugListView extends View {
	public static final String VIEW_DEF_ID = "project-bug-list";

	void setTitle(String title);

	HasSearchHandlers<BugSearchCriteria> getSearchHandlers();

	HasSelectableItemHandlers<SimpleBug> getSelectableItemHandlers();

	IPagedBeanTable<BugSearchCriteria, SimpleBug> getPagedBeanTable();
}
