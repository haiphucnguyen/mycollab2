package com.esofthead.mycollab.module.project.view.defect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class BugListPresenter extends AbstractPresenter<BugListView>
		implements ListPresenter<BugSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private BugService bugService;

	private BugSearchCriteria searchCriteria;

	private boolean isSelectAll = false;

	public BugListPresenter() {
		super(BugListView.class);

		bugService = AppContext.getSpringBean(BugService.class);

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
				new SearchHandler<BugSearchCriteria>() {

					@Override
					public void onSearch(BugSearchCriteria criteria) {
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
						Collection<SimpleBug> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleBug item : currentDataList) {
							item.setSelected(false);
							CheckBox checkBox = (CheckBox) item.getExtraData();
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
				new SelectableItemHandler<SimpleBug>() {

					@Override
					public void onSelect(SimpleBug item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleBug> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleBug item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleBug> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleBug item : currentDataList) {
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
		BugContainer bugContainer = (BugContainer) container;
		bugContainer.addComponent(view.getWidget());

		doSearch((BugSearchCriteria) data.getParams());
	}

	@Override
	public void doSearch(BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleBug> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleBug item : currentDataList) {
				keyList.add(item.getId());
			}

			if (keyList.size() > 0) {
				bugService
						.removeWithSession(keyList, AppContext.getUsername());
				doSearch(searchCriteria);
			}
		} else {
			bugService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}

	}

}
