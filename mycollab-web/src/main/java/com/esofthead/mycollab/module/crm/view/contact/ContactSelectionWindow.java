package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ContactSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private ContactSearchCriteria searchCriteria;
	private ContactTableDisplay tableItem;
	private FieldSelection fieldSelection;

	public ContactSelectionWindow(FieldSelection fieldSelection) {
		super("Contact Name Lookup");
		this.setWidth("900px");
		this.fieldSelection = fieldSelection;
		this.setModal(true);
	}

	public void show() {
		searchCriteria = new ContactSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createAccountList();

		ContactSimpleSearchPanel contactSimpleSearchPanel = new ContactSimpleSearchPanel();
		contactSimpleSearchPanel
				.addSearchHandler(new SearchHandler<ContactSearchCriteria>() {

					@Override
					public void onSearch(ContactSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		layout.addComponent(contactSimpleSearchPanel);
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	@SuppressWarnings("serial")
	private void createAccountList() {
		tableItem = new ContactTableDisplay(Arrays.asList(
				ContactTableFieldDef.name, ContactTableFieldDef.phoneOffice,
				ContactTableFieldDef.email, ContactTableFieldDef.assignUser));
		tableItem.setWidth("100%");

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleContact contact = (SimpleContact) event.getData();
						if ("contactName".equals(event.getFieldName())) {
							fieldSelection.fireValueChange(contact);
							ContactSelectionWindow.this.getParent()
									.removeWindow(ContactSelectionWindow.this);
						}
					}
				});

	}
}
