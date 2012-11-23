package com.esofthead.mycollab.module.crm.view.contact;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface ContactListView extends View {
	void displayContacts(List<SimpleContact> contacts, int currentPage,
			int totalPages);

	void enableActionControls(int numOfSelectedItem);

	void disableActionControls();
	
	HasSelectableItemHandlers<SimpleContact> getSelectableItemHandlers();

	HasSearchHandlers<ContactSearchCriteria> getSearchHandlers();

	HasPagableHandlers getPagableHandlers();

	HasSelectionOptionHandlers getOptionSelectionHandlers();

	HasPopupActionHandlers getPopupActionHandlers();

	interface ContactListPresenter extends Presenter {
		
		void doDefaultSearch();

		void doSearch(ContactSearchCriteria searchCriteria);
	}
}
