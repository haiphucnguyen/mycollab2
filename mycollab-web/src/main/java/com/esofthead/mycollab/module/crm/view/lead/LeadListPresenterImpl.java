package com.esofthead.mycollab.module.crm.view.lead;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadListView.LeadListPresenter;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class LeadListPresenterImpl extends CrmGenericPresenter<LeadListView>
		implements LeadListPresenter {

	private LeadService leadService;

	private SearchRequest<LeadSearchCriteria> searchRequest;

	private List<SimpleLead> currentListData = new ArrayList<SimpleLead>();

	private SelectionModel<SimpleLead> selectionModel = new SelectionModel<SimpleLead>();

	public LeadListPresenterImpl(LeadListView view) {
		this.view = view;
		leadService = AppContext.getSpringBean(LeadService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<LeadSearchCriteria>() {

					@Override
					public void onSearch(LeadSearchCriteria criteria) {
						searchRequest = new SearchRequest<LeadSearchCriteria>(
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

						for (SimpleLead lead : selectionModel) {
							CheckBox checkBox = (CheckBox) lead.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						selectionModel.removeAll();
						for (SimpleLead lead : currentListData) {
							CheckBox checkBox = (CheckBox) lead.getExtraData();
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
		
		view.getSelectableItemHandlers().addSelectableItemHandler(new SelectableItemHandler<SimpleLead>() {
			
			@Override
			public void onSelect(SimpleLead item) {
				if (selectionModel.isSelected(item)) {
					selectionModel.removeSelection(item);
				} else {
					selectionModel.addSelection(item);
				}

				checkWhetherEnableTableActionControl();
				
			}
		});
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
		LeadSearchCriteria leadSearchCriteria = new LeadSearchCriteria();
		leadSearchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(leadSearchCriteria);
	}

	@Override
	public void doSearch(LeadSearchCriteria searchCriteria) {
		this.searchRequest = new SearchRequest<LeadSearchCriteria>(
				searchCriteria, 1, SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
		doSearch();
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		int totalCount = leadService.getTotalCount(searchRequest
				.getSearchCriteria());
		int totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		currentListData = leadService.findPagableListByCriteria(searchRequest);
		view.displayLeads(currentListData, searchRequest.getCurrentPage(),
				totalPage);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleLead lead : selectionModel) {
			keyList.add(lead.getId());
		}

		if (keyList.size() > 0) {
			leadService.removeWithSession(keyList, AppContext.getUsername());
			doSearch();
		}
	}
}
