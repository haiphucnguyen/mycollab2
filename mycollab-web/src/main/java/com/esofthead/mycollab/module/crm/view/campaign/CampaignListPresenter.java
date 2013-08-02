package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class CampaignListPresenter extends
		CrmGenericPresenter<CampaignListView> implements
		ListPresenter<CampaignSearchCriteria>,
		MassUpdatePresenter<CampaignWithBLOBs> {

	private static final long serialVersionUID = 1L;
	private static final String[] EXPORT_VISIBLE_COLUMNS = new String[] {
			"campaignname", "status", "type", "expectedrevenue", "enddate",
			"assignUserFullName" };
	private static final String[] EXPORT_DISPLAY_NAMES = new String[] {
			"Campaign", "Status", "Type", "Expected Revenue", "End Date",
			"Assign User" };
	private CampaignService campaignService;
	private CampaignSearchCriteria searchCriteria;
	private boolean isSelectAll = false;

	public CampaignListPresenter() {
		super(CampaignListView.class);
		campaignService = AppContext.getSpringBean(CampaignService.class);

		view.getPagedBeanTable().addPagableHandler(new PagableHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void move(int newPageNumber) {
				pageChange();
			}

			@Override
			public void displayItemChange(int numOfItems) {
				pageChange();
			}

			private void pageChange() {
				if (isSelectAll) {
					selectAllItemsInCurrentPage();
				}

				checkWhetherEnableTableActionControl();
			}
		});

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<CampaignSearchCriteria>() {
					@Override
					public void onSearch(CampaignSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {
					@Override
					public void onSelectCurrentPage() {
						isSelectAll = false;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						Collection<SimpleCampaign> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleCampaign campaign : currentDataList) {
							campaign.setSelected(false);
							CheckBox checkBox = (CheckBox) campaign
									.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

					}

					@Override
					public void onSelectAll() {
						isSelectAll = true;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {
					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							ConfirmDialogExt.show(
									view.getWindow(),
									LocalizationHelper
											.getMessage(
													GenericI18Enum.DELETE_DIALOG_TITLE,
													ApplicationProperties
															.getString(ApplicationProperties.SITE_NAME)),
									LocalizationHelper
											.getMessage(GenericI18Enum.DELETE_MULTIPLE_ITEMS_DIALOG_MESSAGE),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												deleteSelectedItems();
											}
										}
									});
						} else if ("mail".equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						} else if ("export".equals(id)) {
							Resource res = null;

							if (isSelectAll) {
								res = new StreamResource(
										new ExportStreamResource.AllItems<CampaignSearchCriteria>(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES,
												AppContext
														.getSpringBean(CampaignService.class),
												searchCriteria), "export.csv",
										view.getApplication());
							} else {
								List tableData = view.getPagedBeanTable()
										.getCurrentDataList();
								res = new StreamResource(
										new ExportStreamResource.ListData(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES, tableData),
										"export.csv", view.getApplication());
							}

							view.getWidget().getWindow().open(res, "_blank");
						} else if ("massUpdate".equals(id)) {
							MassUpdateCampaignWindow massUpdateWindow = new MassUpdateCampaignWindow(
									LocalizationHelper
											.getMessage(
													GenericI18Enum.MASS_UPDATE_WINDOW_TITLE,
													"Campaign"),
									CampaignListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleCampaign>() {
					@Override
					public void onSelect(SimpleCampaign item) {
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleCampaign> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleCampaign campaign : currentDataList) {
			campaign.setSelected(true);
			CheckBox checkBox = (CheckBox) campaign.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleCampaign> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleCampaign campaign : currentDataList) {
			if (campaign.isSelected()) {
				countItems++;
			}
		}
		if (countItems > 0) {
			view.enableActionControls(countItems);
		} else {
			view.disableActionControls();
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CAMPAIGN)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CAMPAIGNS_HEADER));

			super.onGo(container, data);
			doSearch((CampaignSearchCriteria) data.getParams());

			AppContext.addFragment("crm/campaign/list", LocalizationHelper
					.getMessage(GenericI18Enum.BROWSER_LIST_ITEMS_TITLE,
							"Campaign"));
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public void doSearch(CampaignSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleCampaign> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleCampaign item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				campaignService.removeWithSession(keyList,
						AppContext.getUsername());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			campaignService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public void massUpdate(CampaignWithBLOBs value) {
		if (!isSelectAll) {
			Collection<SimpleCampaign> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleCampaign item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}
			if (keyList.size() > 0) {
				campaignService.massUpdateWithSession(value, keyList);
				doSearch(searchCriteria);
			}
		} else {
			campaignService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}
}
