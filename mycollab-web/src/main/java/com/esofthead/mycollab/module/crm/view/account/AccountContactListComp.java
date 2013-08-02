package com.esofthead.mycollab.module.crm.view.account;

import java.util.Arrays;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableFieldDef;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
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

	public void displayContacts(final Account account) {
		this.account = account;
		loadContacts();
	}

	@SuppressWarnings("serial")
	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setCaption("New Contact");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							final SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		final Button selectBtn = new Button("Select from existing contacts",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						final AccountContactSelectionWindow contactsWindow = new AccountContactSelectionWindow(
								AccountContactListComp.this);
						final ContactSearchCriteria criteria = new ContactSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(contactsWindow);
						contactsWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);

		addHeaderElement(controlsBtn);

		tableItem = new ContactTableDisplay(Arrays.asList(
				ContactTableFieldDef.name, ContactTableFieldDef.title,
				ContactTableFieldDef.email, ContactTableFieldDef.phoneOffice,
				ContactTableFieldDef.action));

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleContact contact = (SimpleContact) event
								.getData();
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
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final SimpleContact contact = tableItem.getBeanByIndex(itemId);
				final HorizontalLayout controlLayout = new HorizontalLayout();
				final Button editBtn = new Button(null,
						new Button.ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new ContactEvent.GotoRead(
												AccountContactListComp.this,
												contact.getId()));
							}
						});
				editBtn.setStyleName("link");
				editBtn.setIcon(MyCollabResource
						.newResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				final Button deleteBtn = new Button(null,
						new Button.ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {
								ConfirmDialogExt
										.show(AppContext.getApplication()
												.getMainWindow(),
												LocalizationHelper
														.getMessage(
																GenericI18Enum.DELETE_DIALOG_TITLE,
																ApplicationProperties
																		.getString(ApplicationProperties.SITE_NAME)),
												LocalizationHelper
														.getMessage(CrmCommonI18nEnum.DIALOG_DELETE_RELATIONSHIP_TITLE),
												LocalizationHelper
														.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
												LocalizationHelper
														.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
												new ConfirmDialog.Listener() {
													private static final long serialVersionUID = 1L;

													@Override
													public void onClose(
															final ConfirmDialog dialog) {
														if (dialog
																.isConfirmed()) {
															final ContactService contactService = AppContext
																	.getSpringBean(ContactService.class);
															Contact contact = new Contact();
															contact.setId(contact
																	.getId());
															contact.setAccountid(null);
															contactService
																	.updateWithSession(
																			contact,
																			AppContext
																					.getUsername());

															AccountContactListComp.this
																	.refresh();
														}
													}
												});
							}
						});
				deleteBtn.setStyleName("link");
				deleteBtn.setIcon(MyCollabResource
						.newResource("icons/16/delete.png"));
				controlLayout.addComponent(deleteBtn);
				return controlLayout;
			}
		});
		contentContainer.addComponent(tableItem);

	}

	private void loadContacts() {
		final ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	public void refresh() {
		loadContacts();
	}

	@Override
	public void setSelectedItems(final Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}
}
