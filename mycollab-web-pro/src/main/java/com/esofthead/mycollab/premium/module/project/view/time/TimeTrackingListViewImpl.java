package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.reporting.ExportTimeLoggingStreamResource;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.components.AbstractTimeTrackingDisplayComp;
import com.esofthead.mycollab.module.project.ui.components.TimeTrackingDateOrderComponent;
import com.esofthead.mycollab.module.project.ui.components.TimeTrackingUserOrderComponent;
import com.esofthead.mycollab.module.project.view.time.TimeTableFieldDef;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class TimeTrackingListViewImpl extends AbstractPageView implements
        TimeTrackingListView {
    private static final long serialVersionUID = 3742030333599796165L;

    private ItemTimeLoggingSearchPanel itemTimeLoggingPanel;

    private VerticalLayout timeTrackingWrapper;

    private final ItemTimeLoggingService itemTimeLoggingService;
    private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;

    private SplitButton exportButtonControl;

    private final Label lbTimeRange;

    public TimeTrackingListViewImpl() {
        this.setMargin(new MarginInfo(false, true, false, true));

        final MHorizontalLayout headerWrapper = new MHorizontalLayout().withSpacing(false).withMargin(false)
                .withWidth("100%");
        headerWrapper.addStyleName(UIConstants.LAYOUT_LOG);

        this.itemTimeLoggingService = ApplicationContextUtil
                .getSpringBean(ItemTimeLoggingService.class);

        this.itemTimeLoggingPanel = new ItemTimeLoggingSearchPanel();
        this.itemTimeLoggingPanel
                .addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
                    @Override
                    public void onSearch(
                            final ItemTimeLoggingSearchCriteria criteria) {
                        TimeTrackingListViewImpl.this
                                .setSearchCriteria(criteria);
                    }
                });

        this.addComponent(this.itemTimeLoggingPanel);
        this.itemTimeLoggingPanel.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow(
                        TimeTrackingListViewImpl.this);
                UI.getCurrent().addWindow(addTimeEntry);
            }
        });


        final MHorizontalLayout headerLayout = new MHorizontalLayout().withSpacing(true).withMargin(false).withWidth("100%");
        headerWrapper.addComponent(headerLayout);

        this.lbTimeRange = new Label("", ContentMode.HTML);
        this.lbTimeRange.addStyleName(UIConstants.TEXT_LOG_DATE_FULL);
        headerLayout.addComponent(this.lbTimeRange);
        headerLayout.setComponentAlignment(this.lbTimeRange,
                Alignment.MIDDLE_LEFT);
        headerLayout.setExpandRatio(this.lbTimeRange, 1.0f);

        Button exportBtn = new Button("Export", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                exportButtonControl.setPopupVisible(true);
            }
        });
        exportButtonControl = new SplitButton(exportBtn);
        exportButtonControl.setStyleName(UIConstants.THEME_GRAY_LINK);
        exportButtonControl.setIcon(FontAwesome.EXTERNAL_LINK);

        VerticalLayout popupButtonsControl = new VerticalLayout();
        exportButtonControl.setContent(popupButtonsControl);

        Button exportPdfBtn = new Button("Pdf");
        FileDownloader exportPdfDownloader = new FileDownloader(
                constructStreamResource(ReportExportType.PDF));
        exportPdfDownloader.extend(exportPdfBtn);
        exportPdfBtn.setIcon(FontAwesome.FILE_PDF_O);
        exportPdfBtn.setStyleName("link");
        popupButtonsControl.addComponent(exportPdfBtn);

        Button exportExcelBtn = new Button("Excel");
        FileDownloader excelDownloader = new FileDownloader(
                constructStreamResource(ReportExportType.EXCEL));
        excelDownloader.extend(exportExcelBtn);
        exportExcelBtn.setIcon(FontAwesome.FILE_EXCEL_O);
        exportExcelBtn.setStyleName("link");
        popupButtonsControl.addComponent(exportExcelBtn);

        headerLayout.addComponent(exportButtonControl);
        headerLayout.setComponentAlignment(this.exportButtonControl,
                Alignment.MIDDLE_RIGHT);
        this.addComponent(headerWrapper);

        timeTrackingWrapper = new VerticalLayout();
        timeTrackingWrapper.setWidth("100%");
        this.addComponent(timeTrackingWrapper);
    }

    private StreamResource constructStreamResource(ReportExportType exportType) {
        final String title = "Time of project "
                + ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
                .getProject().getName() != null) ? CurrentProjectVariables
                .getProject().getName() : "");
        ExportTimeLoggingStreamResource exportStream = new ExportTimeLoggingStreamResource(
                title, exportType,
                ApplicationContextUtil
                        .getSpringBean(ItemTimeLoggingService.class),
                TimeTrackingListViewImpl.this.itemTimeLogginSearchCriteria);
        return new StreamResource(exportStream,
                ExportTimeLoggingStreamResource
                        .getDefaultExportFileName(exportType));
    }

    private void setTimeRange() {
        final RangeDateSearchField rangeField = this.itemTimeLogginSearchCriteria
                .getRangeDate();

        final String fromDate = AppContext.formatDate(rangeField.getFrom());
        final String toDate = AppContext.formatDate(rangeField.getTo());

        this.itemTimeLogginSearchCriteria.setIsBillable(new BooleanSearchField(
                true));
        Double billableHour = this.itemTimeLoggingService
                .getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);
        if (billableHour == null || billableHour < 0) {
            billableHour = 0d;
        }

        this.itemTimeLogginSearchCriteria.setIsBillable(new BooleanSearchField(
                false));
        Double nonBillableHour = this.itemTimeLoggingService
                .getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);
        if (nonBillableHour == null || nonBillableHour < 0) {
            nonBillableHour = 0d;
        }

        this.itemTimeLogginSearchCriteria.setIsBillable(null);
        final Double totalHour = this.itemTimeLoggingService
                .getTotalHoursByCriteria(this.itemTimeLogginSearchCriteria);

        if (totalHour != null && totalHour > 0) {
            this.lbTimeRange
                    .setValue(AppContext
                            .getMessage(
                                    TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                                    fromDate, toDate, totalHour, billableHour,
                                    nonBillableHour));
        } else {
            this.lbTimeRange.setValue(AppContext.getMessage(
                    TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
        }
    }

    @Override
    public void setSearchCriteria(
            final ItemTimeLoggingSearchCriteria searchCriteria) {
        this.itemTimeLogginSearchCriteria = searchCriteria;
        refresh();
    }

    @Override
    public void refresh() {
        this.setTimeRange();
        timeTrackingWrapper.removeAllComponents();

        AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp(this.itemTimeLoggingPanel
                .getGroupBy());
        timeTrackingWrapper.addComponent(timeDisplayComp);
        timeDisplayComp.queryData(itemTimeLogginSearchCriteria,
                this.itemTimeLoggingPanel.getOrderBy());
    }

    private AbstractTimeTrackingDisplayComp buildTimeTrackingComp(String groupBy) {
        if ("Date".equals(groupBy)) {
            return new TimeTrackingDateOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary, TimeTableFieldDef.logUser,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable,
                    TimeTableFieldDef.id), this.tableClickListener);

        } else if ("User".equals(groupBy)) {
            return new TimeTrackingUserOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary, TimeTableFieldDef.logForDate,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable,
                    TimeTableFieldDef.id), this.tableClickListener);
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    private TableClickListener tableClickListener = new TableClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void itemClick(final TableClickEvent event) {
            final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event
                    .getData();
            if ("summary".equals(event.getFieldName())) {
                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance()
                            .post(new BugEvent.GotoRead(this, itemLogging
                                    .getTypeid()));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging
                        .getType())) {
                    EventBusFactory.getInstance().post(
                            new TaskEvent.GotoRead(this, itemLogging
                                    .getTypeid()));
                } else if (ProjectTypeConstants.RISK.equals(itemLogging
                        .getType())) {
                    EventBusFactory.getInstance().post(
                            new RiskEvent.GotoRead(this, itemLogging
                                    .getTypeid()));
                } else if (ProjectTypeConstants.PROBLEM.equals(itemLogging
                        .getType())) {
                    EventBusFactory.getInstance().post(
                            new ProblemEvent.GotoRead(this, itemLogging
                                    .getTypeid()));
                }
            } else if ("delete".equals(event.getFieldName())) {
                ConfirmDialogExt
                        .show(UI.getCurrent(),
                                AppContext.getMessage(
                                        GenericI18Enum.DIALOG_DELETE_TITLE,
                                        SiteConfiguration.getSiteName()),
                                AppContext
                                        .getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                                AppContext
                                        .getMessage(GenericI18Enum.BUTTON_YES),
                                AppContext
                                        .getMessage(GenericI18Enum.BUTTON_NO),
                                new ConfirmDialog.Listener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void onClose(ConfirmDialog dialog) {
                                        if (dialog.isConfirmed()) {
                                            ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
                                                    .getSpringBean(ItemTimeLoggingService.class);
                                            itemTimeLoggingService.removeWithSession(
                                                    itemLogging.getId(),
                                                    AppContext.getUsername(),
                                                    AppContext.getAccountId());

                                            refresh();
                                        }
                                    }
                                });

            } else if ("edit".equals(event.getFieldName())) {
                TimeTrackingEditViewWindow timeTrackingEdit = new TimeTrackingEditViewWindow(
                        TimeTrackingListViewImpl.this, itemLogging);
                UI.getCurrent().addWindow(timeTrackingEdit);
            }
        }
    };
}
