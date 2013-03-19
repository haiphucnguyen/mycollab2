package com.esofthead.mycollab.module.crm.view.contact;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContactTableDisplay extends
		PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact> {

	public ContactTableDisplay(final String[] visibleColumns,
			String[] columnHeaders) {
		super(AppContext.getSpringBean(ContactService.class),
				SimpleContact.class, visibleColumns, columnHeaders);
		this.addGeneratedColumn("selected", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						SimpleContact contact = ContactTableDisplay.this
								.getBeanByIndex(itemId);
						ContactTableDisplay.this.fireSelectItemEvent(contact);
						fireTableEvent(new TableClickEvent(
								ContactTableDisplay.this, contact, "selected"));
					}
				});
				SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				contact.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("contactName", new ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(contact.getContactName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										ContactTableDisplay.this, contact,
										"contactName"));
							}
						});
				b.addStyleName("medium-text");
				return b;
			}
		});

		this.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDateTime(contact
						.getCreatedtime()));

			}
		});

		this.addGeneratedColumn("email", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				return new EmailLink(contact.getEmail());
			}
		});

		this.addGeneratedColumn("accountName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				List<Account> accounts = contact.getAccounts();
				if (accounts != null && !accounts.isEmpty()) {
					VerticalLayout hLayout = new VerticalLayout();
					for (final Account account : accounts) {
						Button accountLink = new Button(account
								.getAccountname(), new Button.ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(
												ContactTableDisplay.this,
												account.getId()));
							}
						});
						accountLink.setStyleName("link");
						hLayout.addComponent(accountLink);
					}

					return hLayout;
				} else {
					return new Label();
				}
			}
		});

		this.setColumnExpandRatio("contactName", 1.0f);

		this.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		this.setColumnWidth("title", UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("accountName", UIConstants.TABLE_EX_LABEL_WIDTH);
		this.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		this.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		this.setWidth("100%");
	}
}
