package com.mycollab.pro.module.project.view.time;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.query.LazyValueInjector;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.BugEvent;
import com.mycollab.module.project.event.RiskEvent;
import com.mycollab.module.project.event.TaskEvent;
import com.mycollab.module.project.event.TimeTrackingEvent;
import com.mycollab.module.project.fielddef.TimeTableFieldDef;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.pro.module.project.ui.components.AbstractTimeTrackingDisplayComp;
import com.mycollab.pro.module.project.ui.components.TimeTrackingDateOrderComponent;
import com.mycollab.pro.module.project.ui.components.TimeTrackingUserOrderComponent;
import com.mycollab.pro.module.project.view.reports.TimesheetCustomizeReportOutputWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickEvent;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
@ViewComponent
public class TimeTrackingListViewImpl extends AbstractVerticalPageView implements TimeTrackingListView {
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
        final MHorizontalLayout headerWrapper = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withFullWidth();
        itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);

        searchPanel = new ItemTimeLoggingSearchPanel();
        searchPanel.addSearchHandler(criteria -> setSearchCriteria(criteria));

        this.addComponent(searchPanel);

        final MHorizontalLayout headerLayout = new MHorizontalLayout().withFullWidth();
        headerWrapper.addComponent(headerLayout);

        lbTimeRange = ELabel.h3("");

        MButton printBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new TimesheetCustomizeReportOutputWindow(new LazyValueInjector() {
            @Override
            protected Object doEval() {
                return searchCriteria;
            }
        }))).withIcon(FontAwesome.PRINT).withStyleName(WebUIConstants.BUTTON_OPTION).withDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_EXPORT));

        headerLayout.with(lbTimeRange, printBtn).expand(lbTimeRange);
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

    private void setTimeRange() {
        final String fromDate = UserUIContext.formatDate(searchPanel.getFromDate());
        final String toDate = UserUIContext.formatDate(searchPanel.getToDate());

        searchCriteria.setIsBillable(new BooleanSearchField(true));
        Double billableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setIsBillable(new BooleanSearchField(false));
        Double nonBillableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setIsBillable(null);
        final Double totalHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        if (totalHour > 0) {
            lbTimeRange.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                    fromDate, toDate, totalHour, billableHour, nonBillableHour));
        } else {
            lbTimeRange.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
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
        timeTrackingWrapper.addComponent(ELabel.h3(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
    }

    private void displayTimeEntries() {
        timeTrackingWrapper.removeAllComponents();
        setTimeRange();

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp(searchPanel.getGroupBy());
        timeTrackingWrapper.addComponent(timeDisplayComp);

        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                int pages = totalCount / 20;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPageableListByCriteria(new
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
        if (UserUIContext.getMessage(DayI18nEnum.OPT_DATE).equals(groupBy)) {
            return new TimeTrackingDateOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary(), TimeTableFieldDef.logUser(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(),
                    TimeTableFieldDef.id()), this.tableClickListener);

        } else if (UserUIContext.getMessage(UserI18nEnum.SINGLE).equals(groupBy)) {
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
            if ("name".equals(event.getFieldName())) {
                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new BugEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new TaskEvent.GotoRead(this, itemLogging.getTypeid()));
                } else if (ProjectTypeConstants.RISK.equals(itemLogging.getType())) {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, itemLogging.getTypeid()));
                }
            } else if ("delete".equals(event.getFieldName())) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, MyCollabUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                                itemTimeLoggingService.removeWithSession(itemLogging, UserUIContext.getUsername(), MyCollabUI.getAccountId());
                                displayTimeEntries();
                            }
                        });
            } else if ("edit".equals(event.getFieldName())) {
                TimeTrackingEditViewWindow timeTrackingEdit = new TimeTrackingEditViewWindow(TimeTrackingListViewImpl.this, itemLogging);
                UI.getCurrent().addWindow(timeTrackingEdit);
            }
        }
    };
}
