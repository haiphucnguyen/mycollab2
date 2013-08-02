package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface EventListView extends View {

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<EventSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<SimpleEvent> getSelectableItemHandlers();

    IPagedBeanTable<EventSearchCriteria, SimpleEvent> getPagedBeanTable();
}
