package com.esofthead.mycollab.module.crm.view.account;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountContact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class AccountContactListComp extends
		RelatedListComp<SimpleContact, ContactSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private Account account;

	public AccountContactListComp() {
		super("Contacts");
		initUI();
	}

	public void displayContacts(Account account) {
		this.account = account;
		loadContacts();
	}

	private void loadContacts() {
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		this.setSearchCriteria(criteria);
	}

	@SuppressWarnings("serial")
	private void initUI() {
		VerticalLayout contentContainer = (VerticalLayout) bodyContent;
		contentContainer.setSpacing(true);

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.setCaption("New Contact");
		controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		Button selectBtn = new Button("Select from existing contacts",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						AccountContactSelectionWindow contactsWindow = new AccountContactSelectionWindow(
								AccountContactListComp.this);
						ContactSearchCriteria criteria = new ContactSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(contactsWindow);
						contactsWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);

		contentContainer.addComponent(controlsBtn);

		tableItem = new ContactTableDisplay(
				new String[] { "contactName", "title", "email", "officephone",
						"id" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_TITLE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ACTION_HEADER) });

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
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(
											AccountContactListComp.this,
											contact.getId()));
						}
					}
				});

		tableItem.addGeneratedColumn("id", new ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleContact contact = tableItem.getBeanByIndex(itemId);
				HorizontalLayout controlLayout = new HorizontalLayout();
				Button editBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoRead(
										AccountContactListComp.this, contact
												.getId()));
					}
				});
				editBtn.setStyleName("link");
				editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				ConfirmDialog.setFactory(new ConfirmDialogFactory());
				Button deleteBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						ConfirmDialog.show(
								AppContext.getApplication().getMainWindow(),
								LocalizationHelper
										.getMessage(GenericI18Enum.DELETE_DIALOG_TITLE),
								LocalizationHelper
										.getMessage(CrmCommonI18nEnum.DIALOG_DELETE_RELATIONSHIP_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											AccountService accountService = AppContext
													.getSpringBean(AccountService.class);
											AccountContact associateContact = new AccountContact();
											associateContact
													.setAccountid(account
															.getId());
											associateContact
													.setContactid(contact
															.getId());
											accountService
													.removeAccountContactRelationship(associateContact);
											AccountContactListComp.this
													.refresh();
										}
									}
								});
					}
				});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
				controlLayout.addComponent(deleteBtn);
				return controlLayout;
			}
		});
		contentContainer.addComponent(tableItem);

	}

	@Override
	public void setSelectedItems(Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}

	@Override
	public void refresh() {
		loadContacts();
	}
}
