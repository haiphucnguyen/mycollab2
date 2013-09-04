package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.file.resource.ExportItemsStreamResource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;

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
			PopupActionHandler {

		private ListSelectionPresenter presenter;

		public DefaultPopupActionHandler(ListSelectionPresenter presenter) {
			this.presenter = presenter;
		}

		@Override
		public void onSelect(String id, String caption) {
			if ("delete".equals(id)) {
				ConfirmDialogExt
						.show(presenter.getView().getWindow(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
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
											presenter.deleteSelectedItems();
										}
									}
								});

			} else if ("exportCsv".equals(id)) {
				Resource res = null;
				AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
						.getView()).getPagedBeanTable();
				if (presenter.isSelectAll) {
					res = new StreamResource(
							new ExportItemsStreamResource.AllItems("",
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.CSV,
									presenter.getSearchService(),
									presenter.searchCriteria,
									getReportModelClassType()), "export.csv",
							presenter.view.getApplication());
				} else {

					res = new StreamResource(
							new ExportItemsStreamResource.ListData("",
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.CSV,
									presenter.getSelectedItems(),
									getReportModelClassType()), "export.csv",
							presenter.view.getApplication());
				}

				presenter.view.getWidget().getWindow().open(res, "_blank");
			} else if ("exportPdf".equals(id)) {
				Resource res = null;
				AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
						.getView()).getPagedBeanTable();
				if (presenter.isSelectAll) {
					res = new StreamResource(
							new ExportItemsStreamResource.AllItems(
									getReportTitle(),
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.PDF,
									presenter.getSearchService(),
									presenter.searchCriteria,
									getReportModelClassType()), "export.pdf",
							presenter.view.getApplication());
				} else {

					res = new StreamResource(
							new ExportItemsStreamResource.ListData(
									getReportTitle(),
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.PDF,
									presenter.getSelectedItems(),
									getReportModelClassType()), "export.pdf",
							presenter.view.getApplication());
				}

				presenter.view.getWidget().getWindow().open(res, "_blank");
			} else if ("exportExcel".equals(id)) {
				Resource res = null;
				AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
						.getView()).getPagedBeanTable();
				if (presenter.isSelectAll) {
					res = new StreamResource(
							new ExportItemsStreamResource.AllItems(
									getReportTitle(),
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.EXCEL,
									presenter.getSearchService(),
									presenter.searchCriteria,
									getReportModelClassType()), "export.xlsx",
							presenter.view.getApplication());
				} else {

					res = new StreamResource(
							new ExportItemsStreamResource.ListData(
									getReportTitle(),
									pagedBeanTable.getDisplayColumns(),
									ReportExportType.EXCEL,
									presenter.getSelectedItems(),
									getReportModelClassType()), "export.xlsx",
							presenter.view.getApplication());
				}

				presenter.view.getWidget().getWindow().open(res, "_blank");
			}

		}

		protected abstract Class getReportModelClassType();

		protected abstract String getReportTitle();
	}

}
