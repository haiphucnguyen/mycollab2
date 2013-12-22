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
package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.vaadin.ui.CheckBox;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 * @param <V>
 * @param <S>
 * @param <B>
 */
public abstract class ListSelectionPresenter<V extends ListView<S, B>, S extends SearchCriteria, B extends ValuedBean>
		extends AbstractPresenter<V> {
	private static final long serialVersionUID = 1L;

	protected boolean isSelectAll = false;
	protected S searchCriteria;

	public ListSelectionPresenter(Class<V> viewClass) {
		super(viewClass);

		cacheableView.getSearchHandlers().addSearchHandler(new SearchHandler<S>() {
			@Override
			public void onSearch(S criteria) {
				doSearch(criteria);
			}
		});

		cacheableView.getPagedBeanTable().addPagableHandler(new PagableHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void move(int newPageNumber) {
				pageChange();
			}

			private void pageChange() {
				if (isSelectAll) {
					selectAllItemsInCurrentPage();
				}

				checkWhetherEnableTableActionControl();
			}
		});

		cacheableView.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSelectCurrentPage() {
						isSelectAll = false;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						Collection<B> currentDataList = cacheableView
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (B item : currentDataList) {
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

						checkWhetherEnableTableActionControl();
					}
				});

		cacheableView.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<B>() {
					@Override
					public void onSelect(B item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	protected void selectAllItemsInCurrentPage() {
		Collection<B> currentDataList = cacheableView.getPagedBeanTable()
				.getCurrentDataList();
		for (B item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	public void doSearch(S searchCriteria) {
		this.searchCriteria = searchCriteria;
		cacheableView.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	protected void checkWhetherEnableTableActionControl() {
		Collection<B> currentDataList = cacheableView.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (B item : currentDataList) {
			if (item.isSelected()) {
				countItems++;
			}
		}
		if (countItems > 0) {
			cacheableView.enableActionControls(countItems);
		} else {
			cacheableView.disableActionControls();
		}
	}

	protected List<B> getSelectedItems() {
		List<B> items = new ArrayList<B>();
		Collection<B> currentDataList = cacheableView.getPagedBeanTable()
				.getCurrentDataList();
		for (B item : currentDataList) {
			if (item.isSelected()) {
				items.add(item);
			}
		}

		return items;
	}

	abstract public ISearchableService<S> getSearchService();

	abstract protected void deleteSelectedItems();
}
