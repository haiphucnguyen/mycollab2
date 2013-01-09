package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ContactListViewImpl extends AbstractView implements
		ContactListView {

	private static final long serialVersionUID = 1L;
	private final ContactSearchPanel contactSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private ContactTableDisplay tableItem;
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
		tableItem = new ContactTableDisplay(new String[] { "selected",
				"contactName", "title", "accountName", "email", "officephone",
				"assignUserFullName" }, new String[] { "", "Name", "Title",
				"Account Name", "Email", "Office Phone", "User" });

		tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleContact contact = (SimpleContact) event.getData();
						if ("contactName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(
											ContactListViewImpl.this, contact
													.getId()));
						} else if ("accountName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(
											ContactListViewImpl.this, contact
													.getAccountId()));
						}
					}
				});

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
	public IPagedBeanTable<ContactSearchCriteria, SimpleContact> getPagedBeanTable() {
		return tableItem;
	}
}
