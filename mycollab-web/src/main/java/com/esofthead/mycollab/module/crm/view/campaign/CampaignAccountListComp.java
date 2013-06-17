/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.account.AccountTableDisplay;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class CampaignAccountListComp extends
		RelatedListComp<SimpleAccount, AccountSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private CampaignWithBLOBs campaign;

	public CampaignAccountListComp() {
		super("Accounts");
		initUI();
	}

	public void displayAccounts(CampaignWithBLOBs campaign) {
		this.campaign = campaign;
		loadAccounts();
	}

	private void loadAccounts() {
		AccountSearchCriteria criteria = new AccountSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setCampaignId(new NumberSearchField(SearchField.AND, campaign
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
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setCaption("New Account");
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecordGreen.png"));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		Button selectBtn = new Button("Select from existing accounts",
				new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
						CampaignAccountSelectionWindow accountsWindow = new CampaignAccountSelectionWindow(
								CampaignAccountListComp.this);
						AccountSearchCriteria criteria = new AccountSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(accountsWindow);
						accountsWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(MyCollabResource.newResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_ACCOUNT));
		addHeaderElement(controlsBtn);

		tableItem = new AccountTableDisplay(
				new String[] { "accountname", "phoneoffice", "email", "city",
						"id" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_ACCOUNT_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_CITY_HEADER),
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
						SimpleAccount account = (SimpleAccount) event.getData();
						if ("accountname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(
											CampaignAccountListComp.this,
											account.getId()));
						}
					}
				});

		tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleAccount account = (SimpleAccount) tableItem
						.getBeanByIndex(itemId);
				HorizontalLayout controlLayout = new HorizontalLayout();
				Button editBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoEdit(
										CampaignAccountListComp.this, account));
					}
				});
				editBtn.setStyleName("link");
				editBtn.setIcon(MyCollabResource
						.newResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				Button deleteBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
						ConfirmDialog.show(
								AppContext.getApplication().getMainWindow(),
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
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											CampaignService campaignService = AppContext
													.getSpringBean(CampaignService.class);
											CampaignAccount associateAccount = new CampaignAccount();
											associateAccount
													.setAccountid(account
															.getId());
											associateAccount
													.setCampaignid(campaign
															.getId());
											campaignService
													.removeCampaignAccountRelationship(associateAccount);
											CampaignAccountListComp.this
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

	@Override
	public void setSelectedItems(Set selectedItems) {
		fireSelectedRelatedItems(selectedItems);
	}

	@Override
	public void refresh() {
		loadAccounts();
	}
}
