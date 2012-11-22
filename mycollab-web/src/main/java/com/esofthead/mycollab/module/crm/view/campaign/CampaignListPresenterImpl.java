package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListView.CampaignListPresenter;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class CampaignListPresenterImpl extends
		CrmGenericPresenter<CampaignListView> implements CampaignListPresenter {
	private CampaignService campaignService;

	private SearchRequest<CampaignSearchCriteria> searchRequest;

	private List<SimpleCampaign> currentListData = new ArrayList<SimpleCampaign>();

	private SelectionModel<SimpleCampaign> selectionModel = new SelectionModel<SimpleCampaign>();

	public CampaignListPresenterImpl(CampaignListView view) {
		this.view = view;
		view.setPresenter(this);
		campaignService = AppContext.getSpringBean(CampaignService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<CampaignSearchCriteria>() {

					@Override
					public void onSearch(CampaignSearchCriteria criteria) {
						searchRequest = new SearchRequest<CampaignSearchCriteria>(
								criteria, 0,
								SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
						doSearch();
					}
				});

		view.getPagableHandlers().addPagableHandler(new PagableHandler() {

			@Override
			public void move(int newPageNumber) {
				searchRequest.setCurrentPage(newPageNumber);
				doSearch();
			}

			@Override
			public void displayItemChange(int numOfItems) {
				searchRequest.setNumberOfItems(numOfItems);
				doSearch();
			}
		});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelect() {
						selectionModel.addSelections(currentListData);

						for (SimpleCampaign campaign : selectionModel) {
							CheckBox checkBox = (CheckBox) campaign
									.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						selectionModel.removeAll();
						for (SimpleCampaign campaign : currentListData) {
							CheckBox checkBox = (CheckBox) campaign
									.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

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
	}

	@Override
	public void onItemSelect(SimpleCampaign campaign) {
		if (selectionModel.isSelected(campaign)) {
			selectionModel.removeSelection(campaign);
		} else {
			selectionModel.addSelection(campaign);
		}

		checkWhetherEnableTableActionControl();
	}

	private void checkWhetherEnableTableActionControl() {
		if (selectionModel.size() > 0) {
			view.enableActionControls(selectionModel.size());
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
		this.searchRequest = new SearchRequest<CampaignSearchCriteria>(
				searchCriteria, 1, SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
		doSearch();
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		int totalCount = campaignService.getTotalCount(searchRequest
				.getSearchCriteria());
		int totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		currentListData = campaignService
				.findPagableListByCriteria(searchRequest);
		view.displayCampaigns(currentListData, searchRequest.getCurrentPage(),
				totalPage);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleCampaign campaign : selectionModel) {
			keyList.add(campaign.getId());
		}

		if (keyList.size() > 0) {
			campaignService
					.removeWithSession(keyList, AppContext.getUsername());
			doSearch();
		}
	}
}
