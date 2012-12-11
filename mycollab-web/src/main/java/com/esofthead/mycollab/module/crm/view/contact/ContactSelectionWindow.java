package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ContactSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private ContactSearchCriteria searchCriteria;

	private PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact> tableItem;

	private FieldSelection fieldSelection;

	public ContactSelectionWindow(FieldSelection fieldSelection) {
		super("Contact Name Lookup");
		this.setWidth("800px");

		this.fieldSelection = fieldSelection;
	}

	public void show() {
		searchCriteria = new ContactSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createAccountList();

		layout.addComponent(createSearchPanel());
		layout.addComponent(tableItem);
		layout.addComponent(tableItem.createControls());
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private ComponentContainer createSearchPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		TextField valueField = new TextField();
		layout.addComponent(valueField);

		ValueComboBox group = new ValueComboBox(false, new String[] {
				"Organization Name", "Billing City", "Webiste", "Phone",
				"Assigned To" });
		layout.addComponent(group);

		Button searchBtn = new Button("Search");
		layout.addComponent(searchBtn);
		return layout;
	}

	private void createAccountList() {
		tableItem = new PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact>(
				AppContext.getSpringBean(ContactService.class),
				SimpleContact.class, new String[] { "contactName",
						"officephone", "email", "assignUserFullName" },
				new String[] { "Name", "Phone", "Email", "Assign User" });
		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("contactName", 1.0f);
		tableItem
				.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		tableItem.addGeneratedColumn("contactName", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				final SimpleContact contact = tableItem
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(contact.getContactName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fieldSelection.fireValueChange(contact);
								ContactSelectionWindow.this.getParent()
										.removeWindow(
												ContactSelectionWindow.this);
							}
						});
				return b;
			}
		});

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleContact contact = tableItem
						.getBeanByIndex(itemId);
				return new EmailLink(contact.getEmail());

			}
		});
	}
}
