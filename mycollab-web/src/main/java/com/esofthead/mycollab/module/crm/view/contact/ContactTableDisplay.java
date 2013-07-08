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
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
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

	public ContactTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public ContactTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public ContactTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(ContactService.class),
				SimpleContact.class, viewId, requiredColumn, displayColumns);

		addGeneratedColumn("selected", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						final SimpleContact contact = ContactTableDisplay.this
								.getBeanByIndex(itemId);
						ContactTableDisplay.this.fireSelectItemEvent(contact);
						fireTableEvent(new TableClickEvent(
								ContactTableDisplay.this, contact, "selected"));
					}
				});
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				contact.setExtraData(cb);
				return cb;
			}
		});

		addGeneratedColumn("contactName", new ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				final ButtonLink b = new ButtonLink(contact.getContactName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										ContactTableDisplay.this, contact,
										"contactName"));
							}
						});
				return b;
			}
		});

		addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDateTime(contact
						.getCreatedtime()));

			}
		});

		addGeneratedColumn("email", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				return new EmailLink(contact.getEmail());
			}
		});

		addGeneratedColumn("accountName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleContact contact = ContactTableDisplay.this
						.getBeanByIndex(itemId);
				if (contact.getAccountName() != null) {
					Button accountLink = new Button(contact.getAccountName(),
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									fireTableEvent(new TableClickEvent(
											ContactTableDisplay.this, contact,
											"accountName"));

								}
							});
					accountLink.setStyleName("link");
					return accountLink;
				} else {
					return new Label();
				}
			}
		});

		this.setWidth("100%");
	}
}
