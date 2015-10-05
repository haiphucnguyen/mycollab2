package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
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
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.OptionPopupContent;
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
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.List;

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
    private SplitButton exportButtonControl;
    private final Label lbTimeRange;

    public TimeTrackingListViewImpl() {
        this.setMargin(new MarginInfo(false, true, false, true));

        final MHorizontalLayout headerWrapper = new MHorizontalLayout().withSpacing(false).withWidth("100%");

        itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

        this.searchPanel = new ItemTimeLoggingSearchPanel();
        this.searchPanel.addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
            @Override
            public void onSearch(ItemTimeLoggingSearchCriteria criteria) {
                setSearchCriteria(criteria);
            }
        });

        this.addComponent(this.searchPanel);
        this.searchPanel.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow(TimeTrackingListViewImpl.this);
                UI.getCurrent().addWindow(addTimeEntry);
            }
        });


        final MHorizontalLayout headerLayout = new MHorizontalLayout().withWidth("100%");
        headerWrapper.addComponent(headerLayout);

        lbTimeRange = new Label("", ContentMode.HTML);
        lbTimeRange.addStyleName("h2");
        headerLayout.with(lbTimeRange).withAlign(lbTimeRange, Alignment.MIDDLE_LEFT).expand(lbTimeRange);

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

        OptionPopupContent popupButtonsControl = new OptionPopupContent();
        exportButtonControl.setContent(popupButtonsControl);

        Button exportPdfBtn = new Button("Pdf");
        FileDownloader exportPdfDownloader = new FileDownloader(constructStreamResource(ReportExportType.PDF));
        exportPdfDownloader.extend(exportPdfBtn);
        exportPdfBtn.setIcon(FontAwesome.FILE_PDF_O);
        popupButtonsControl.addOption(exportPdfBtn);

        Button exportExcelBtn = new Button("Excel");
        FileDownloader excelDownloader = new FileDownloader(constructStreamResource(ReportExportType.EXCEL));
        excelDownloader.extend(exportExcelBtn);
        exportExcelBtn.setIcon(FontAwesome.FILE_EXCEL_O);
        popupButtonsControl.addOption(exportExcelBtn);

        headerLayout.with(exportButtonControl).withAlign(exportButtonControl, Alignment.MIDDLE_RIGHT);
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
        //TODO: make report for time logging
        return null;
    }

    private void setTimeRange() {
        final RangeDateSearchField rangeField = searchCriteria.getRangeDate();

        final String fromDate = AppContext.formatDate(rangeField.getFrom());
        final String toDate = AppContext.formatDate(rangeField.getTo());

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
        this.searchCriteria = searchCriteria;
        refresh();
    }

    @Override
    public void refresh() {
        this.setTimeRange();
        queryTimeLoggings(searchCriteria);
    }

    private void queryTimeLoggings(final ItemTimeLoggingSearchCriteria searchCriteria) {
        timeTrackingWrapper.removeAllComponents();

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp(searchPanel.getGroupBy());
        timeTrackingWrapper.addComponent(timeDisplayComp);

        new Thread() {
            @Override
            public void run() {
                UI.getCurrent().access(new Runnable() {
                    @Override
                    public void run() {
                        ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
                        int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                        int pages = totalCount / 20;
                        for (int page = 0; page < pages + 1; page++) {
                            List<SimpleItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPagableListByCriteria(new
                                    SearchRequest<>(searchCriteria, page + 1, 20));
                            for (SimpleItemTimeLogging item : itemTimeLoggings) {
                                timeDisplayComp.insertItem(item);
                            }
                        }
                        timeDisplayComp.flush();
                        UI.getCurrent().push();
                    }
                });
            }
        }.start();
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
            final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event.getData();
            if ("summary".equals(event.getFieldName())) {
                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new BugEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new TaskEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.RISK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.PROBLEM.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new ProblemEvent.GotoRead(this, itemLogging.getTypeid()));
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
                                    refresh();
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
