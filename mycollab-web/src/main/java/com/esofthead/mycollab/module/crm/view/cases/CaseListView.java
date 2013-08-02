package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface CaseListView extends View {
	public static final String VIEW_DEF_ID = "crm-case-list";

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<CaseSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<SimpleCase> getSelectableItemHandlers();

    IPagedBeanTable<CaseSearchCriteria, SimpleCase> getPagedBeanTable();
}
