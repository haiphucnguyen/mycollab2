package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;

public interface ContactListView extends View {
	
	IPagedBeanTable<ContactService, ContactSearchCriteria, SimpleContact> getPagedBeanTable();

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();
	
	HasSelectableItemHandlers<SimpleContact> getSelectableItemHandlers();

	HasSearchHandlers<ContactSearchCriteria> getSearchHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();
}
