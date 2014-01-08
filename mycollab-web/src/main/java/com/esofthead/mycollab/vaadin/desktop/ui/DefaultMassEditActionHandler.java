package com.esofthead.mycollab.vaadin.desktop.ui;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.file.resource.SimpleGridExportItemsStreamResource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.RpParameterBuilder;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public abstract class DefaultMassEditActionHandler implements
		MassItemActionHandler {

	private ListSelectionPresenter presenter;

	public DefaultMassEditActionHandler(ListSelectionPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onSelect(String id) {
		if (MassItemActionHandler.DELETE_ACTION.equals(id)) {
			ConfirmDialogExt
					.show(UI.getCurrent(),
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
		} else {
			onSelectExtra(id);
		}

	}

	@Override
	public StreamResource buildStreamResource(String id) {
		StreamResource res = null;
		if (MassItemActionHandler.EXPORT_CSV_ACTION.equals(id)) {
			AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
					.initView()).getPagedBeanTable();
			if (presenter.isSelectAll) {
				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.AllItems("",
								new RpParameterBuilder(pagedBeanTable
										.getDisplayColumns()),
								ReportExportType.CSV,
								presenter.getSearchService(),
								presenter.searchCriteria,
								getReportModelClassType()), "export.csv");
			} else {
				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.ListData("",
								new RpParameterBuilder(pagedBeanTable
										.getDisplayColumns()),
								ReportExportType.CSV,
								presenter.getSelectedItems(),
								getReportModelClassType()), "export.csv");
			}
		} else if (MassItemActionHandler.EXPORT_PDF_ACTION.equals(id)) {
			AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
					.initView()).getPagedBeanTable();
			if (presenter.isSelectAll) {
				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.AllItems(
								getReportTitle(), new RpParameterBuilder(
										pagedBeanTable.getDisplayColumns()),
								ReportExportType.PDF,
								presenter.getSearchService(),
								presenter.searchCriteria,
								getReportModelClassType()), "export.pdf");
			} else {
				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.ListData(
								getReportTitle(), new RpParameterBuilder(
										pagedBeanTable.getDisplayColumns()),
								ReportExportType.PDF,
								presenter.getSelectedItems(),
								getReportModelClassType()), "export.pdf");
			}
		} else if (MassItemActionHandler.EXPORT_EXCEL_ACTION.equals(id)) {
			AbstractPagedBeanTable pagedBeanTable = ((ListView) presenter
					.initView()).getPagedBeanTable();
			if (presenter.isSelectAll) {
				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.AllItems(
								getReportTitle(), new RpParameterBuilder(
										pagedBeanTable.getDisplayColumns()),
								ReportExportType.EXCEL,
								presenter.getSearchService(),
								presenter.searchCriteria,
								getReportModelClassType()), "export.xlsx");
			} else {

				res = new StreamResource(
						new SimpleGridExportItemsStreamResource.ListData(
								getReportTitle(), new RpParameterBuilder(
										pagedBeanTable.getDisplayColumns()),
								ReportExportType.EXCEL,
								presenter.getSelectedItems(),
								getReportModelClassType()), "export.xlsx");
			}
		}

		return res;
	}

	protected abstract void onSelectExtra(String id);

	protected abstract Class<?> getReportModelClassType();

	protected abstract String getReportTitle();
}
