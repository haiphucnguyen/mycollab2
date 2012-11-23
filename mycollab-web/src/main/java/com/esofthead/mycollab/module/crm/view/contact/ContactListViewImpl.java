package com.esofthead.mycollab.module.crm.view.contact;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.ContactSearchPanel;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class ContactListViewImpl extends AbstractView implements
		ContactListView {
	private static final long serialVersionUID = 1L;

	private final ContactSearchPanel contactSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable<SimpleContact> tableItem;

	private final VerticalLayout contactListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	private ContactListPresenter presenter;

	public ContactListViewImpl() {
		this.setSpacing(true);

		contactSearchPanel = new ContactSearchPanel();
		this.addComponent(contactSearchPanel);

		contactListLayout = new VerticalLayout();
		contactListLayout.setSpacing(true);
		this.addComponent(contactListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable<SimpleContact>();

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
						@SuppressWarnings("unchecked")
						SimpleContact account = ((PagedBeanTable<SimpleContact>) source)
								.getBeanByIndex(itemId);
						presenter.onItemSelect(account);

					}
				});

				@SuppressWarnings("unchecked")
				SimpleContact account = ((PagedBeanTable<SimpleContact>) source)
						.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings("unchecked")
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleContact account = ((PagedBeanTable<SimpleContact>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					Link l = new Link();
					l.setResource(new ExternalResource("mailto:"
							+ account.getEmail()));
					l.setCaption(account.getEmail());
					return l;
				} else {
					return new Label("");
				}

			}
		});

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleContact account = ((PagedBeanTable<SimpleContact>) source)
						.getBeanByIndex(itemId);
				if (account != null) {
					Label l = new Label();

					l.setCaption(account.getCreatedtime() + "");
					return l;
				} else {
					return new Label("");
				}

			}
		});
		
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("title", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

		tableItem.setWidth("100%");
		contactListLayout.addComponent(constructTableActionControls());
		contactListLayout.addComponent(tableItem);
		contactListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public void displayContacts(List<SimpleContact> accounts, int currentPage,
			int totalPages) {
		tableItem.setCurrentPage(currentPage);
		tableItem.setTotalPage(totalPages);

		BeanItemContainer<SimpleContact> container = new BeanItemContainer<SimpleContact>(
				SimpleContact.class, accounts);
		tableItem.setContainerDataSource(container);

		tableItem.setVisibleColumns(new String[] { "selected", "contactName",
				"title", "accountName", "email", "officephone",
				"assignUserFullName"});
		tableItem.setColumnHeaders(new String[] { "", "Name", "Title",
				"Account Name", "Email", "Office Phone", "User" });

	}

	@Override
	public HasSearchHandlers<ContactSearchCriteria> getSearchHandlers() {
		return contactSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton();
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
	public void setPresenter(ContactListPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public HasPagableHandlers getPagableHandlers() {
		return tableItem;
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}
}
