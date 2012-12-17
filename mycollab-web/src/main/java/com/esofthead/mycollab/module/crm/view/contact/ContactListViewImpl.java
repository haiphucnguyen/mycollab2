package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ContactListViewImpl extends AbstractView implements
		ContactListView {
	private static final long serialVersionUID = 1L;

	private final ContactSearchPanel contactSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact> tableItem;

	private final VerticalLayout contactListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public ContactListViewImpl() {
		this.setSpacing(true);

		contactSearchPanel = new ContactSearchPanel();
		this.addComponent(contactSearchPanel);

		contactListLayout = new VerticalLayout();
		contactListLayout.setSpacing(true);
		this.addComponent(contactListLayout);

		generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact>(
				AppContext.getSpringBean(ContactService.class),
				SimpleContact.class, new String[] { "selected", "contactName",
						"title", "accountName", "email", "officephone",
						"assignUserFullName" }, new String[] { "", "Name",
						"Title", "Account Name", "Email", "Office Phone",
						"User" });

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						SimpleContact contact = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(contact);

					}
				});

				SimpleContact contact = tableItem.getBeanByIndex(itemId);
				contact.setExtraData(cb);
				return cb;
			}
		});

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

		tableItem.addGeneratedColumn("accountName", new ColumnGenerator() {

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleContact contact = tableItem.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(contact.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this, contact
												.getAccountid()));
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

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleContact contact = tableItem.getBeanByIndex(itemId);
				return new Label(AppContext.formatDateTime(contact
						.getCreatedtime()));

			}
		});

		tableItem.setColumnExpandRatio("contactName", 1.0f);

		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("title", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem
				.setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem
				.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		tableItem.setWidth("100%");
		contactListLayout.addComponent(constructTableActionControls());
		contactListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<ContactSearchCriteria> getSearchHandlers() {
		return contactSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setEnabled(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleContact> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<ContactService, ContactSearchCriteria, SimpleContact> getPagedBeanTable() {
		return tableItem;
	}
}
