package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;

public interface ListView<S extends SearchCriteria, B> extends View {
	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();

	HasSearchHandlers<S> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	HasSelectableItemHandlers<B> getSelectableItemHandlers();

	AbstractPagedBeanTable<S, B> getPagedBeanTable();
}
