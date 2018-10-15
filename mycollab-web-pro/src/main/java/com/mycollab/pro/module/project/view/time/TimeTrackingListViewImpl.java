package com.mycollab.pro.module.project.view.time;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.query.LazyValueInjector;
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
import com.mycollab.vaadin.*;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickEvent;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.Collections;
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

    private VerticalLayout timeTrackingWrapper;
    private ELabel lbTimeRange;

    private String groupByState;
    private String sortDirection;

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

        ItemTimeLoggingSearchPanel searchPanel = new ItemTimeLoggingSearchPanel();
        searchPanel.addSearchHandler(this::setSearchCriteria);

        MHorizontalLayout groupWrapLayout = new MHorizontalLayout();
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        groupWrapLayout.addComponent(new ELabel(UserUIContext.getMessage(GenericI18Enum.ACTION_SORT)));
        final ComboBox sortCombo = new ValueComboBox(false, UserUIContext.getMessage(GenericI18Enum.OPT_SORT_DESCENDING),
                UserUIContext.getMessage(GenericI18Enum.OPT_SORT_ASCENDING));
        sortCombo.addValueChangeListener(valueChangeEvent -> {
            String sortValue = (String) sortCombo.getValue();
            if (UserUIContext.getMessage(GenericI18Enum.OPT_SORT_ASCENDING).equals(sortValue)) {
                sortDirection = SearchCriteria.ASC;
            } else {
                sortDirection = SearchCriteria.DESC;
            }
            displayTimeEntries();
        });
        sortDirection = SearchCriteria.DESC;
        groupWrapLayout.addComponent(sortCombo);

        groupWrapLayout.addComponent(new ELabel(UserUIContext.getMessage(GenericI18Enum.OPT_GROUP)));
        ValueComboBox groupField = new ValueComboBox(false, UserUIContext.getMessage(DayI18nEnum.OPT_DATE),
                UserUIContext.getMessage(UserI18nEnum.SINGLE));
        groupField.addValueChangeListener(valueChangeEvent -> groupByState = (String) groupField.getValue());
        groupByState = UserUIContext.getMessage(DayI18nEnum.OPT_DATE);
        groupWrapLayout.addComponent(groupField);

        MButton printBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new TimesheetCustomizeReportOutputWindow(new LazyValueInjector() {
            @Override
            protected Object doEval() {
                return searchCriteria;
            }
        }))).withIcon(VaadinIcons.PRINT).withStyleName(WebThemes.BUTTON_OPTION).withDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_EXPORT));
        groupWrapLayout.addComponent(printBtn);

        MButton createBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow();
            UI.getCurrent().addWindow(addTimeEntry);
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.PLUS);
        createBtn.setVisible(!CurrentProjectVariables.isProjectArchived() &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TIME));
        groupWrapLayout.addComponent(createBtn);
        searchPanel.addHeaderRight(groupWrapLayout);

        this.addComponent(searchPanel);

        final MHorizontalLayout headerLayout = new MHorizontalLayout().withFullWidth();
        headerWrapper.addComponent(headerLayout);

        lbTimeRange = ELabel.h3("");
        headerLayout.with(lbTimeRange).expand(lbTimeRange);
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

        searchCriteria.setBillable(new BooleanSearchField(true));
        Double billableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setBillable(new BooleanSearchField(false));
        Double nonBillableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setBillable(null);
        final Double totalHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        lbTimeRange.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                totalHour, billableHour, nonBillableHour));
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

        if (UserUIContext.getMessage(DayI18nEnum.OPT_DATE).equals(groupByState)) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("logForDay", sortDirection)));
        } else if (UserUIContext.getMessage(UserI18nEnum.SINGLE).equals(groupByState)) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("logUserFullName", sortDirection)));
        }

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp();
        timeTrackingWrapper.addComponent(timeDisplayComp);

        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                int pages = totalCount / 20;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleItemTimeLogging> itemTimeLoggings = (List<SimpleItemTimeLogging>) itemTimeLoggingService.findPageableListByCriteria(new
                            BasicSearchRequest<>(searchCriteria, page + 1, 20));
                    for (SimpleItemTimeLogging item : itemTimeLoggings) {
                        timeDisplayComp.insertItem(item);
                    }
                }
                timeDisplayComp.flush();
            }
        });
    }

    private AbstractTimeTrackingDisplayComp buildTimeTrackingComp() {
        if (UserUIContext.getMessage(DayI18nEnum.OPT_DATE).equals(groupByState)) {
            return new TimeTrackingDateOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary, TimeTableFieldDef.logUser,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.overtime,
                    TimeTableFieldDef.id), this.tableClickListener);

        } else if (UserUIContext.getMessage(UserI18nEnum.SINGLE).equals(groupByState)) {
            return new TimeTrackingUserOrderComponent(Arrays.asList(
                    TimeTableFieldDef.summary, TimeTableFieldDef.logForDate,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.overtime,
                    TimeTableFieldDef.id), this.tableClickListener);
        } else {
            throw new MyCollabException("Do not support view type: " + groupByState);
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
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                                itemTimeLoggingService.removeWithSession(itemLogging, UserUIContext.getUsername(), AppUI.getAccountId());
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
