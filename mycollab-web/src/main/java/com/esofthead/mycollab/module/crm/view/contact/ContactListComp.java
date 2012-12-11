package com.esofthead.mycollab.module.crm.view.contact;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class ContactListComp extends Depot implements IRelatedListHandlers {
	private static final long serialVersionUID = 1L;

	private PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact> tableItem;

	private Set<RelatedListHandler> handlers;

	public ContactListComp() {
		super("Contacts", new VerticalLayout());
		this.setWidth("900px");
		initUI();
	}

	@SuppressWarnings("serial")
	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) content;
		contentContainer.setSpacing(true);

		Button createBtn = new Button("New Contact",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						fireRelatedListHandler();
					}
				});

		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createBtn.setStyleName(BaseTheme.BUTTON_LINK);
		contentContainer.addComponent(createBtn);

		tableItem = new PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact>(
				AppContext.getSpringBean(ContactService.class),
				SimpleContact.class,
				new String[] { "contactName", "title", "email", "officephone",
						"assignUserFullName" },
				new String[] { "Name", "Title", "Email", "Office Phone", "User" });

		tableItem.addGeneratedColumn("contactName", new ColumnGenerator() {

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleContact contact = tableItem.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(contact.getContactName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new ContactEvent.GotoRead(this, contact
												.getId()));
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
				SimpleContact contact = tableItem.getBeanByIndex(itemId);
				return new EmailLink(contact.getEmail());

			}
		});

		tableItem.setColumnExpandRatio("contactName", 1.0f);
		tableItem.setColumnWidth("title", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem
				.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		tableItem.setWidth("100%");
		contentContainer.addComponent(tableItem);
		contentContainer.addComponent(tableItem.createControls());

	}

	public void setSearchCriteria(ContactSearchCriteria searchCriteria) {
		tableItem.setSearchCriteria(searchCriteria);
	}

	private void fireRelatedListHandler() {
		if (handlers != null) {
			for (RelatedListHandler handler : handlers) {
				handler.createNewRelatedItem();
			}
		}
	}

	@Override
	public void addRelatedListHandler(RelatedListHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<RelatedListHandler>();
		}

		handlers.add(handler);
	}

}
