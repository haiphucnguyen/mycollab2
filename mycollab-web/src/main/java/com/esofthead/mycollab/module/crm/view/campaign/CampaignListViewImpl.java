/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Arrays;

import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CampaignListViewImpl extends AbstractPageView implements
		CampaignListView {

	private final CampaignSearchPanel campaignSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private CampaignTableDisplay tableItem;
	private final VerticalLayout campainListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();
	private CampaignImportWindow campaignImportWindow;

	public CampaignListViewImpl() {

		this.campaignSearchPanel = new CampaignSearchPanel();
		this.addComponent(this.campaignSearchPanel);

		this.campainListLayout = new VerticalLayout();
		this.addComponent(this.campainListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {

		this.tableItem = new CampaignTableDisplay(CampaignListView.VIEW_DEF_ID,
				CampaignTableFieldDef.selected, Arrays.asList(
						CampaignTableFieldDef.campaignname,
						CampaignTableFieldDef.status,
						CampaignTableFieldDef.type,
						CampaignTableFieldDef.expectedRevenue,
						CampaignTableFieldDef.endDate,
						CampaignTableFieldDef.assignUser));

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
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		this.selectOptionButton.setSizeUndefined();
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_CAMPAIGN));

		this.tableActionControls = new PopupButtonControl(
				MassItemActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(
				MassItemActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(MassItemActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(MassItemActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));
		this.tableActionControls
				.addOptionItem(
						MassItemActionHandler.MASS_UPDATE_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_MASSUPDATE),
						AppContext
								.canWrite(RolePermissionCollections.CRM_CAMPAIGN));

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
				UI.getCurrent().addWindow(
						new CampaignListCustomizeWindow(
								CampaignListView.VIEW_DEF_ID, tableItem));

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
				campaignImportWindow = new CampaignImportWindow();
				UI.getCurrent().addWindow(campaignImportWindow);
			}
		});
		importBtn.setDescription("Import");
		importBtn.setIcon(MyCollabResource.newResource("icons/16/import.png"));
		importBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		importBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));

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
	public HasMassItemActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable() {
		return this.tableItem;
	}
}
