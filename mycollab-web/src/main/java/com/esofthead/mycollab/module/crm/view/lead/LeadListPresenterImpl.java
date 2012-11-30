package com.esofthead.mycollab.module.crm.view.lead;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class LeadListPresenterImpl extends CrmGenericPresenter<LeadListView>
		implements ListPresenter<LeadSearchCriteria> {

	private LeadService leadService;

	private LeadSearchCriteria searchCriteria;
	
	private boolean isSelectAll = false;

	public LeadListPresenterImpl(final LeadListView view) {
		this.view = view;
		leadService = AppContext.getSpringBean(LeadService.class);
		
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
				new SearchHandler<LeadSearchCriteria>() {

					@Override
					public void onSearch(LeadSearchCriteria criteria) {
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
						Collection<SimpleLead> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleLead item : currentDataList) {
							item.setSelected(false);
							CheckBox checkBox = (CheckBox) item
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
		
		view.getSelectableItemHandlers().addSelectableItemHandler(new SelectableItemHandler<SimpleLead>() {
			
			@Override
			public void onSelect(SimpleLead item) {
				isSelectAll = false;
				item.setSelected(!item.isSelected());

				checkWhetherEnableTableActionControl();
				
			}
		});
	}
	
	private void selectAllItemsInCurrentPage() {
		Collection<SimpleLead> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleLead item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleLead> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleLead item : currentDataList) {
			if (item.isSelected()) {
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
		super.onGo(container, data);
		doSearch((LeadSearchCriteria) data.getParams());
	}

	@Override
	public void doSearch(LeadSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleLead> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleLead item : currentDataList) {
				keyList.add(item.getId());
			}

			if (keyList.size() > 0) {
				leadService.removeWithSession(keyList,
						AppContext.getUsername());
				doSearch(searchCriteria);
			}
		} else {
			leadService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}
	}
}
