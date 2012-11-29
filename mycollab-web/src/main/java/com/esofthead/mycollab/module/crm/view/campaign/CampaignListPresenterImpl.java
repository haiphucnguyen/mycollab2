package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListView.CampaignListPresenter;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class CampaignListPresenterImpl extends
		CrmGenericPresenter<CampaignListView> implements CampaignListPresenter {
	private CampaignService campaignService;

	private CampaignSearchCriteria searchCriteria;

	private boolean isSelectAll = false;

	public CampaignListPresenterImpl(final CampaignListView view) {
		this.view = view;
		campaignService = AppContext.getSpringBean(CampaignService.class);
		
		view.getPagedBeanTable().addPagableHandler(new PagableHandler() {

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
						
					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {

					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							deleteSelectedItems();
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
	public void doDefaultSearch() {
		CampaignSearchCriteria campaignSearchCriteria = new CampaignSearchCriteria();
		campaignSearchCriteria.setSaccountid(new NumberSearchField(
				SearchField.AND, AppContext.getAccountId()));
		doSearch(campaignSearchCriteria);
	}

	@Override
	public void doSearch(CampaignSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleCampaign> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleCampaign account : currentDataList) {
				keyList.add(account.getId());
			}

			if (keyList.size() > 0) {
				campaignService.removeWithSession(keyList, AppContext.getUsername());
				doSearch(searchCriteria);
			}
		} else {
			campaignService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}
	}
}
