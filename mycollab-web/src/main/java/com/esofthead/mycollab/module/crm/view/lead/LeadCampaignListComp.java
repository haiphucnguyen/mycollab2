/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class LeadCampaignListComp extends
		RelatedListComp<SimpleCampaign, CampaignSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private Lead lead;

	public LeadCampaignListComp() {
		super("Campaigns");
		initUI();
	}

	public void displayCampaigns(Lead lead) {
		this.lead = lead;
		loadCampaigns();
	}

	private void loadCampaigns() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setLeadId(new NumberSearchField(SearchField.AND, lead.getId()));
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
		controlsBtn.setCaption("New Campaign");
		controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							SplitButton.SplitButtonClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		Button selectBtn = new Button("Select from existing campaigns",
				new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
						LeadCampaignSelectionWindow leadsWindow = new LeadCampaignSelectionWindow(
								LeadCampaignListComp.this);
						CampaignSearchCriteria criteria = new CampaignSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						getWindow().addWindow(leadsWindow);
						leadsWindow.setSearchCriteria(criteria);
						controlsBtn.setPopupVisible(false);
					}
				});
		selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
		selectBtn.setStyleName("link");
		controlsBtn.addComponent(selectBtn);
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		contentContainer.addComponent(controlsBtn);

		tableItem = new CampaignTableDisplay(
				new String[] { "campaignname", "status", "type", "enddate",
						"id" },
				new String[] {
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_TYPE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_END_DATE_HEADER),
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
						SimpleCampaign campaign = (SimpleCampaign) event
								.getData();
						if ("campaignname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(
											LeadCampaignListComp.this, campaign
													.getId()));
						}
					}
				});

		tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleCampaign campaign = (SimpleCampaign) tableItem
						.getBeanByIndex(itemId);
				HorizontalLayout controlLayout = new HorizontalLayout();
				Button editBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoEdit(
										LeadCampaignListComp.this, campaign));
					}
				});
				editBtn.setStyleName("link");
				editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
				controlLayout.addComponent(editBtn);

				Button deleteBtn = new Button(null, new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {
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
											CampaignService campaignService = AppContext
													.getSpringBean(CampaignService.class);
											CampaignLead associateLead = new CampaignLead();
											associateLead.setLeadid(campaign
													.getId());
											associateLead
													.setCampaignid(campaign
															.getId());
											campaignService
													.removeCampaignLeadRelationship(associateLead);
											LeadCampaignListComp.this.refresh();
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
		loadCampaigns();
	}

}
