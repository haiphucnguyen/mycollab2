package com.mycollab.vaadin.web.ui;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.reporting.ReportExportType;
import com.mycollab.reporting.ReportTemplateExecutor;
import com.mycollab.reporting.RpFieldsBuilder;
import com.mycollab.reporting.SimpleReportTemplateExecutor;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.MassItemActionHandler;
import com.mycollab.vaadin.events.ViewItemAction;
import com.mycollab.vaadin.reporting.ReportStreamSource;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.UI;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public abstract class DefaultMassEditActionHandler implements MassItemActionHandler {
    private ListSelectionPresenter presenter;

    public DefaultMassEditActionHandler(ListSelectionPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSelect(String id) {
        if (ViewItemAction.DELETE_ACTION().equals(id)) {
            ConfirmDialogExt.show(UI.getCurrent(), UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_MULTIPLE_ITEMS_MESSAGE),
                    UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                    UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                    confirmDialog -> {
                        if (confirmDialog.isConfirmed()) {
                            presenter.deleteSelectedItems();
                        }
                    });
        } else {
            onSelectExtra(id);
        }

    }

    @Override
    public StreamResource buildStreamResource(ReportExportType exportType) {
        IPagedBeanTable pagedBeanTable = ((IListView) presenter.getView()).getPagedBeanTable();
        final Map<String, Object> addtionalParamters = new HashMap<>();
        addtionalParamters.put("siteUrl", AppUI.getSiteUrl());
        addtionalParamters.put(SimpleReportTemplateExecutor.CRITERIA, presenter.searchCriteria);
        ReportTemplateExecutor reportTemplateExecutor;
        if (presenter.isSelectAll) {
            reportTemplateExecutor = new SimpleReportTemplateExecutor.AllItems(UserUIContext.getUserTimeZone(),
                    UserUIContext.getUserLocale(), getReportTitle(),
                    new RpFieldsBuilder(pagedBeanTable.getDisplayColumns()), exportType, getReportModelClassType(),
                    presenter.getSearchService());
        } else {
            reportTemplateExecutor = new SimpleReportTemplateExecutor.ListData(UserUIContext.getUserTimeZone(),
                    UserUIContext.getUserLocale(), getReportTitle(),
                    new RpFieldsBuilder(pagedBeanTable.getDisplayColumns()), exportType, presenter.getSelectedItems(),
                    getReportModelClassType());
        }
        return new StreamResource(new ReportStreamSource(reportTemplateExecutor) {
            @Override
            protected void initReportParameters(Map<String, Object> parameters) {
                parameters.putAll(addtionalParamters);
            }
        }, exportType.getDefaultFileName());
    }

    protected abstract void onSelectExtra(String id);

    protected abstract Class<?> getReportModelClassType();

    protected abstract String getReportTitle();
}
