package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TimeTrackingEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.time.TimeTableFieldDef;
import com.esofthead.mycollab.pro.module.project.ui.components.AbstractTimeTrackingDisplayComp;
import com.esofthead.mycollab.pro.module.project.ui.components.TimeTrackingDateOrderComponent;
import com.esofthead.mycollab.pro.module.project.ui.components.TimeTrackingUserOrderComponent;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.ReportStreamSource;
import com.esofthead.mycollab.reporting.RpFieldsBuilder;
import com.esofthead.mycollab.reporting.SimpleReportTemplateExecutor;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.AsyncInvoker;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.peter.buttongroup.ButtonGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@ViewComponent
public class TimeTrackingListViewImpl extends AbstractPageView implements TimeTrackingListView {
    private static final long serialVersionUID = 3742030333599796165L;

    private ItemTimeLoggingService itemTimeLoggingService;
    private ItemTimeLoggingSearchCriteria searchCriteria;

    private ItemTimeLoggingSearchPanel searchPanel;
    private VerticalLayout timeTrackingWrapper;
    private final ELabel lbTimeRange;

    private ApplicationEventListener<TimeTrackingEvent.TimeLoggingEntryChange> timeEntryChangeHandler = new
            ApplicationEventListener<TimeTrackingEvent.TimeLoggingEntryChange>() {
                @Subscribe
                @Override
                public void handle(TimeTrackingEvent.TimeLoggingEntryChange event) {
                    displayTimeEntries();
                }
            };

    public TimeTrackingListViewImpl() {
        this.withMargin(false).withStyleName("hdr-view");
        final MHorizontalLayout headerWrapper = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withWidth("100%");
        itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

        searchPanel = new ItemTimeLoggingSearchPanel();
        searchPanel.addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
            @Override
            public void onSearch(ItemTimeLoggingSearchCriteria criteria) {
                setSearchCriteria(criteria);
            }
        });

        this.addComponent(searchPanel);
        searchPanel.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow(TimeTrackingListViewImpl.this);
                UI.getCurrent().addWindow(addTimeEntry);
            }
        });

        final MHorizontalLayout headerLayout = new MHorizontalLayout().withWidth("100%");
        headerWrapper.addComponent(headerLayout);

        lbTimeRange = ELabel.h3("");

        MButton exportPdfBtn = new MButton("").withIcon(FontAwesome.FILE_PDF_O).withStyleName(UIConstants
                .BUTTON_OPTION).withDescription("Export to PDF");
        FileDownloader exportPdfDownloader = new FileDownloader(constructStreamResource(ReportExportType.PDF));
        exportPdfDownloader.extend(exportPdfBtn);

        MButton exportExcelBtn = new MButton("").withIcon(FontAwesome.FILE_EXCEL_O).withStyleName(UIConstants
                .BUTTON_OPTION).withDescription("Export to Excel");
        FileDownloader excelDownloader = new FileDownloader(constructStreamResource(ReportExportType.EXCEL));
        excelDownloader.extend(exportExcelBtn);

        ButtonGroup exportButtonControl = new ButtonGroup();
        exportButtonControl.addButton(exportPdfBtn);
        exportButtonControl.addButton(exportExcelBtn);

        headerLayout.with(lbTimeRange, exportButtonControl).expand(lbTimeRange).withAlign(exportButtonControl, Alignment.MIDDLE_RIGHT);
        this.addComponent(headerWrapper);

        timeTrackingWrapper = new VerticalLayout();
        timeTrackingWrapper.setWidth("100%");
        this.with(timeTrackingWrapper).expand(timeTrackingWrapper);
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(timeEntryChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(timeEntryChangeHandler);
        super.detach();
    }

    private StreamResource constructStreamResource(ReportExportType exportType) {
        List fields = Arrays.asList(TimeTableFieldDef.summary(), TimeTableFieldDef.logUser(), TimeTableFieldDef.logValue(),
                TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(), TimeTableFieldDef.logForDate());
        SimpleReportTemplateExecutor reportTemplateExecutor = new SimpleReportTemplateExecutor.AllItems<>("Timesheet",
                new RpFieldsBuilder(fields), exportType, SimpleItemTimeLogging.class,
                ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class));
        ReportStreamSource streamSource = new ReportStreamSource(reportTemplateExecutor) {
            @Override
            protected Map<String, Object> initReportParameters() {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("siteUrl", AppContext.getSiteUrl());
                parameters.put(SimpleReportTemplateExecutor.CRITERIA, searchCriteria);
                return parameters;
            }
        };
        return new StreamResource(streamSource, exportType.getDefaultFileName());
    }

    private void setTimeRange() {
        final String fromDate = AppContext.formatDate(searchPanel.getFromDate());
        final String toDate = AppContext.formatDate(searchPanel.getToDate());

        searchCriteria.setIsBillable(new BooleanSearchField(true));
        Double billableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        if (billableHour == null || billableHour < 0) {
            billableHour = 0d;
        }

        searchCriteria.setIsBillable(new BooleanSearchField(false));
        Double nonBillableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        if (nonBillableHour == null || nonBillableHour < 0) {
            nonBillableHour = 0d;
        }

        searchCriteria.setIsBillable(null);
        final Double totalHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        if (totalHour != null && totalHour > 0) {
            lbTimeRange.setValue(AppContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                    fromDate, toDate, totalHour, billableHour, nonBillableHour));
        } else {
            lbTimeRange.setValue(AppContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
        }
    }

    @Override
    public void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TIME)) {
            this.searchCriteria = searchCriteria;
            displayTimeEntries();
        } else {
            displayNoPermissionMessage();
        }
    }

    private void displayNoPermissionMessage() {
        timeTrackingWrapper.removeAllComponents();
        timeTrackingWrapper.addComponent(ELabel.h3("You can not access the specific resource"));
    }

    private void displayTimeEntries() {
        timeTrackingWrapper.removeAllComponents();
        setTimeRange();

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp(searchPanel.getGroupBy());
        timeTrackingWrapper.addComponent(timeDisplayComp);

        AsyncInvoker.access(new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
                int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                int pages = totalCount / 20;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPagableListByCriteria(new
                            BasicSearchRequest<>(searchCriteria, page + 1, 20));
                    for (SimpleItemTimeLogging item : itemTimeLoggings) {
                        timeDisplayComp.insertItem(item);
                    }
                }
                timeDisplayComp.flush();
            }
        });
    }

    private AbstractTimeTrackingDisplayComp buildTimeTrackingComp(String groupBy) {
        if ("Date".equals(groupBy)) {
            return new TimeTrackingDateOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary(), TimeTableFieldDef.logUser(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(),
                    TimeTableFieldDef.id()), this.tableClickListener);

        } else if ("User".equals(groupBy)) {
            return new TimeTrackingUserOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary(), TimeTableFieldDef.logForDate(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(),
                    TimeTableFieldDef.id()), this.tableClickListener);
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    private TableClickListener tableClickListener = new TableClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void itemClick(final TableClickEvent event) {
            final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event.getData();
            if ("summary".equals(event.getFieldName())) {
                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new BugEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new TaskEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.RISK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, itemLogging.getTypeid()));
                }
            } else if ("delete".equals(event.getFieldName())) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
                                AppContext.getSiteName()),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                        new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
                                            .getSpringBean(ItemTimeLoggingService.class);
                                    itemTimeLoggingService.removeWithSession(itemLogging,
                                            AppContext.getUsername(), AppContext.getAccountId());
                                    displayTimeEntries();
                                }
                            }
                        });

            } else if ("edit".equals(event.getFieldName())) {
                TimeTrackingEditViewWindow timeTrackingEdit = new TimeTrackingEditViewWindow(TimeTrackingListViewImpl.this, itemLogging);
                UI.getCurrent().addWindow(timeTrackingEdit);
            }
        }
    };
}
