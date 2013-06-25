package com.esofthead.mycollab.module.crm.view.campaign;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CampaignListViewImpl extends AbstractView implements
		CampaignListView {

	private final CampaignSearchPanel campaignSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private CampaignTableDisplay tableItem;
	private final VerticalLayout campainListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public CampaignListViewImpl() {

		this.campaignSearchPanel = new CampaignSearchPanel();
		this.addComponent(this.campaignSearchPanel);

		this.campainListLayout = new VerticalLayout();
		this.addComponent(this.campainListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {

		if (ScreenSize.hasSupport1024Pixels()) {
			this.tableItem = new CampaignTableDisplay(
					new String[] { "selected", "campaignname", "status",
							"type", "enddate", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_TYPE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_END_DATE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			this.tableItem = new CampaignTableDisplay(
					new String[] { "selected", "campaignname", "status",
							"type", "expectedrevenue", "enddate",
							"assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_TYPE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_EXPECTED_REVENUE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_END_DATE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		}

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleCampaign campaign = (SimpleCampaign) event
								.getData();
						if ("campaignname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(
											CampaignListViewImpl.this, campaign
													.getId()));
						}
					}
				});

		this.campainListLayout
				.addComponent(this.constructTableActionControls());
		this.campainListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers() {
		return this.campaignSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_CAMPAIGN));

		this.tableActionControls = new PopupButtonControl("delete", deleteBtn);
		this.tableActionControls.addOptionItem("mail",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_MAIL));
		this.tableActionControls.addOptionItem("export",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT));
		this.tableActionControls.addOptionItem("massUpdate", LocalizationHelper
				.getMessage(CrmCommonI18nEnum.BUTTON_MASSUPDATE));

		this.tableActionControls.setVisible(false);

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
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
	public HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public IPagedBeanTable<CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable() {
		return this.tableItem;
	}
}
