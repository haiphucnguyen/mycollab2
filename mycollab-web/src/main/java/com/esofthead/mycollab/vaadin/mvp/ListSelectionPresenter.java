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
import com.esofthead.mycollab.vaadin.events.TablePopupActionHandler;
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

		view.getSearchHandlers().addSearchHandler(new SearchHandler<S>() {
			@Override
			public void onSearch(S criteria) {
				doSearch(criteria);
			}
		});

		view.getPagedBeanTable().addPagableHandler(new PagableHandler() {
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

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
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
						Collection<B> currentDataList = view
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

		view.getSelectableItemHandlers().addSelectableItemHandler(
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
		Collection<B> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (B item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	public void doSearch(S searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	protected void checkWhetherEnableTableActionControl() {
		Collection<B> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (B item : currentDataList) {
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

	protected List<B> getSelectedItems() {
		List<B> items = new ArrayList<B>();
		Collection<B> currentDataList = view.getPagedBeanTable()
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

	public static abstract class DefaultPopupActionHandler implements
			TablePopupActionHandler {

		private ListSelectionPresenter presenter;

		public DefaultPopupActionHandler(ListSelectionPresenter presenter) {
			this.presenter = presenter;
		}

		@Override
		public void onSelect(String id, String caption) {
			// TODO: check select items
			// if (TablePopupActionHandler.DELETE_ACTION.equals(id)) {
			// ConfirmDialogExt
			// .show(UI.getCurrent(),
			// LocalizationHelper.getMessage(
			// GenericI18Enum.DELETE_DIALOG_TITLE,
			// SiteConfiguration.getSiteName()),
			// LocalizationHelper
			// .getMessage(GenericI18Enum.DELETE_MULTIPLE_ITEMS_DIALOG_MESSAGE),
			// LocalizationHelper
			// .getMessage(GenericI18Enum.BUTTON_YES_LABEL),
			// LocalizationHelper
			// .getMessage(GenericI18Enum.BUTTON_NO_LABEL),
			// new ConfirmDialog.Listener() {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void onClose(ConfirmDialog dialog) {
			// if (dialog.isConfirmed()) {
			// presenter.deleteSelectedItems();
			// }
			// }
			// });
			//
			// } else if (TablePopupActionHandler.EXPORT_CSV_ACTION.equals(id))
			// {
			// Resource res = null;
			// AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
			// .getView()).getPagedBeanTable();
			// if (presenter.isSelectAll) {
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.AllItems(
			// "",
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.CSV,
			// presenter.getSearchService(),
			// presenter.searchCriteria,
			// getReportModelClassType()), "export.csv");
			// } else {
			//
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.ListData(
			// "",
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.CSV,
			// presenter.getSelectedItems(),
			// getReportModelClassType()), "export.csv");
			// }
			//
			// presenter.view.getWidget().getWindow().open(res, "_blank");
			// } else if (TablePopupActionHandler.EXPORT_PDF_ACTION.equals(id))
			// {
			// Resource res = null;
			// AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
			// .getView()).getPagedBeanTable();
			// if (presenter.isSelectAll) {
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.AllItems(
			// getReportTitle(),
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.PDF,
			// presenter.getSearchService(),
			// presenter.searchCriteria,
			// getReportModelClassType()), "export.pdf");
			// } else {
			//
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.ListData(
			// getReportTitle(),
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.PDF,
			// presenter.getSelectedItems(),
			// getReportModelClassType()), "export.pdf");
			// }
			//
			// presenter.view.getWidget().getWindow().open(res, "_blank");
			// } else if
			// (TablePopupActionHandler.EXPORT_EXCEL_ACTION.equals(id)) {
			// Resource res = null;
			// AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
			// .getView()).getPagedBeanTable();
			// if (presenter.isSelectAll) {
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.AllItems(
			// getReportTitle(),
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.EXCEL,
			// presenter.getSearchService(),
			// presenter.searchCriteria,
			// getReportModelClassType()), "export.xlsx");
			// } else {
			//
			// res = new StreamResource(
			// new SimpleGridExportItemsStreamResource.ListData(
			// getReportTitle(),
			// new RpParameterBuilder(pagedBeanTable
			// .getDisplayColumns()),
			// ReportExportType.EXCEL,
			// presenter.getSelectedItems(),
			// getReportModelClassType()), "export.xlsx");
			// }
			//
			// presenter.view.getWidget().getWindow().open(res, "_blank");
			// } else {
			// onSelectExtra(id, caption);
			// }

		}

		protected abstract void onSelectExtra(String id, String caption);

		protected abstract Class getReportModelClassType();

		protected abstract String getReportTitle();
	}

}
