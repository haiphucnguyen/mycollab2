package com.esofthead.mycollab.module.crm.view.account;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
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

public class AccountLeadListComp extends
		RelatedListComp<SimpleLead, LeadSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private Account account;

	public AccountLeadListComp() {
		super("Leads");

		initUI();
	}

	public void displayLeads(final Account account) {
		this.account = account;
		loadLeads();
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setCaption("New Lead");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecordGreen.png"));
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_LEAD));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void splitButtonClick(
							final SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		final Button selectBtn = new Button("Select from existing leads",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final AccountLeadSelectionWindow leadsWindow = new AccountLeadSelectionWindow(
								AccountLeadListComp.this);
						final LeadSearchCriteria criteria = new LeadSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(leadsWindow);
						leadsWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);

		addHeaderElement(controlsBtn);

		tableItem = new LeadTableDisplay(
				new String[] { "leadName", "status", "officephone", "email",
						"id" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ACTION_HEADER) });

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleLead lead = (SimpleLead) event.getData();
						if ("leadName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new LeadEvent.GotoRead(this, lead.getId()));
						}
					}
				});

		tableItem.addGeneratedColumn("id", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final SimpleLead lead = tableItem.getBeanByIndex(itemId);
				final HorizontalLayout controlLayout = new HorizontalLayout();
				final Button editBtn = new Button(null,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new LeadEvent.GotoRead(
												AccountLeadListComp.this, lead
														.getId()));
							}
						});
				editBtn.setStyleName("link");
				editBtn.setIcon(MyCollabResource
						.newResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				final Button deleteBtn = new Button(null,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								ConfirmDialog.show(
										AppContext.getApplication()
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
												if (dialog.isConfirmed()) {
													final AccountService accountService = AppContext
															.getSpringBean(AccountService.class);
													final AccountLead associateLead = new AccountLead();
													associateLead
															.setAccountid(account
																	.getId());
													associateLead
															.setLeadid(lead
																	.getId());
													accountService
															.removeAccountLeadRelationship(associateLead);
													AccountLeadListComp.this
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

	private void loadLeads() {
		final LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	public void refresh() {
		loadLeads();
	}

	@Override
	public void setSelectedItems(final Set<SimpleLead> selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}
}
