package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignImportWindow;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class OpportunityListViewImpl extends AbstractView implements
		OpportunityListView {

	private static final long serialVersionUID = 1L;
	private final OpportunitySearchPanel opportunitySeachPanel;
	private SelectionOptionButton selectOptionButton;
	private OpportunityTableDisplay tableItem;
	private final VerticalLayout accountListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();
	private CampaignImportWindow opportunityImportWindow;

	public OpportunityListViewImpl() {

		this.opportunitySeachPanel = new OpportunitySearchPanel();
		this.addComponent(this.opportunitySeachPanel);

		this.accountListLayout = new VerticalLayout();
		this.addComponent(this.accountListLayout);

		this.generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {

		this.tableItem = new OpportunityTableDisplay(
				OpportunityListView.VIEW_DEF_ID,
				OpportunityTableFieldDef.selected, Arrays.asList(
						OpportunityTableFieldDef.opportunityName,
						OpportunityTableFieldDef.accountName,
						OpportunityTableFieldDef.saleStage,
						OpportunityTableFieldDef.amount,
						OpportunityTableFieldDef.expectedCloseDate,
						OpportunityTableFieldDef.assignUser));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleOpportunity opportunity = (SimpleOpportunity) event
								.getData();
						if (event.getFieldName().equals("opportunityname")) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoRead(this,
											opportunity.getId()));
						} else if (event.getFieldName().equals("accountname")) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, opportunity
											.getAccountid()));
						}
					}
				});

		this.accountListLayout
				.addComponent(this.constructTableActionControls());
		this.accountListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers() {
		return this.opportunitySeachPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_OPPORTUNITY));

		this.tableActionControls = new PopupButtonControl(
				PopupActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(PopupActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_MAIL));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_CSV_ACTION, LocalizationHelper
						.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT_CSV));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_PDF_ACTION, LocalizationHelper
						.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT_EXCEL));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.MASS_UPDATE_ACTION, LocalizationHelper
						.getMessage(CrmCommonI18nEnum.BUTTON_MASSUPDATE),
				AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));

		this.tableActionControls.setVisible(false);

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(this.selectedItemsNumberLabel, 1.0f);

		Button customizeViewBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(
						new OpportunityListCustomizeWindow(
								OpportunityListView.VIEW_DEF_ID, tableItem));

			}
		});
		customizeViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/customize.png"));
		customizeViewBtn.setDescription("Layout Options");
		customizeViewBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		layout.addComponent(customizeViewBtn);
		layout.setComponentAlignment(customizeViewBtn, Alignment.MIDDLE_RIGHT);

		Button importBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				opportunityImportWindow = new CampaignImportWindow();
				getWindow().addWindow(opportunityImportWindow);
			}
		});
		importBtn.setDescription("Import");
		importBtn.setIcon(MyCollabResource.newResource("icons/16/import.png"));
		importBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		importBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));

		layout.addComponent(importBtn);
		layout.setComponentAlignment(importBtn, Alignment.MIDDLE_RIGHT);

		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleOpportunity> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<OpportunitySearchCriteria, SimpleOpportunity> getPagedBeanTable() {
		return this.tableItem;
	}
}
