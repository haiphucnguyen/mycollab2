package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.RangeDateSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.events.AssignmentEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectGenericTaskService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.assignments.CalendarView;
import com.mycollab.module.project.view.bug.BugAddWindow;
import com.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.mycollab.module.project.view.task.TaskAddWindow;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.view.risk.RiskAddWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventResizeHandler;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.vaadin.peter.buttongroup.ButtonGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class CalendarViewImpl extends AbstractLazyPageView implements CalendarView {
    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormat.forPattern("dd MMMM, yyyy");
    private static final DateTimeFormatter WEEK_FORMATTER = DateTimeFormat.forPattern("dd MMMM, yyyy");
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormat.forPattern("MMMM, yyyy");

    private ApplicationEventListener<AssignmentEvent.NewAssignmentAdd> assignmentChangeHandler = new ApplicationEventListener<AssignmentEvent.NewAssignmentAdd>() {
        @Override
        @Subscribe
        public void handle(AssignmentEvent.NewAssignmentAdd event) {
            String type = event.getTypeVal();
            Integer typeId = event.getTypeIdVal();
            ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setTypeIds(new SetSearchField<>(typeId));
            searchCriteria.setTypes(new SetSearchField<>(type));
            ProjectGenericTaskService assignmentService = AppContextUtil.getSpringBean(ProjectGenericTaskService.class);
            List<ProjectGenericTask> assignments = assignmentService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
            GenericAssignmentProvider provider = (GenericAssignmentProvider) calendar.getEventProvider();
            for (ProjectGenericTask assignment : assignments) {
                GenericAssignmentEvent assignmentEvent = new GenericAssignmentEvent(assignment, false);
                if (provider.containsEvent(assignmentEvent)) {
                    provider.removeEvent(assignmentEvent);
                    provider.addEvent(assignmentEvent);
                } else {
                    provider.addEvent(assignmentEvent);
                }
            }
        }
    };

    private ELabel headerLbl, billableHoursLbl, nonBillableHoursLbl, assignMeLbl, assignOtherLbl, nonAssigneeLbl;
    private Calendar calendar;
    private LocalDate baseDate, startDate, endDate;
    private CalendarMode mode = CalendarMode.MONTHLY;
    private CalendarSearchPanel searchPanel;
    private ProjectGenericTaskSearchCriteria searchCriteria;

    public CalendarViewImpl() {
        this.withMargin(true).withSpacing(true);
        baseDate = new LocalDate();
        searchPanel = new CalendarSearchPanel(true);
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(assignmentChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(assignmentChangeHandler);
        setProjectNavigatorVisibility(true);
        super.detach();
    }

    @Override
    protected void displayView() {
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        setProjectNavigatorVisibility(false);
        removeAllComponents();
        calendar = new Calendar();
        calendar.setEventCaptionAsHtml(true);
        calendar.addStyleName("assignment-calendar");
        calendar.setSizeFull();
        calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
            @Override
            public void eventClick(CalendarComponentEvents.EventClick event) {
                GenericAssignmentEvent calendarEvent = (GenericAssignmentEvent) event.getCalendarEvent();
                ProjectGenericTask assignment = calendarEvent.getAssignment();
                if (ProjectTypeConstants.TASK.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                    ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                    SimpleTask task = taskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new TaskAddWindow(task));
                } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
                    MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                    SimpleMilestone milestone = milestoneService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
                } else if (ProjectTypeConstants.BUG.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
                    BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                    SimpleBug bug = bugService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new BugAddWindow(bug));
                } else if (ProjectTypeConstants.RISK.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
                    RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                    SimpleRisk risk = riskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new RiskAddWindow(risk));
                }
            }
        });

        calendar.setHandler(new CalendarComponentEvents.DateClickHandler() {
            @Override
            public void dateClick(CalendarComponentEvents.DateClickEvent dateClickEvent) {
                if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                    UI.getCurrent().addWindow(new AssignmentAddWindow(dateClickEvent.getDate(),
                            CurrentProjectVariables.getProjectId()));
                }
            }
        });

        calendar.setHandler(new BasicEventMoveHandler() {
            @Override
            public void eventMove(CalendarComponentEvents.MoveEvent event) {
                GenericAssignmentEvent calendarEvent = (GenericAssignmentEvent) event.getCalendarEvent();
                calendarEvent.updateAssociateEntity();
                super.eventMove(event);
            }
        });

        calendar.setHandler(new BasicEventResizeHandler() {
            @Override
            public void eventResize(CalendarComponentEvents.EventResize event) {
                GenericAssignmentEvent calendarEvent = (GenericAssignmentEvent) event.getCalendarEvent();
                calendarEvent.updateAssociateEntity();
                super.eventResize(event);
            }
        });
        MHorizontalLayout noteContainer = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withFullWidth();
        MVerticalLayout helpBlock = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, false))
                .withWidth("80px");
        assignMeLbl = new ELabel("").withStyleName("owner");
        assignOtherLbl = new ELabel("").withStyleName("otheruser");
        nonAssigneeLbl = new ELabel("").withStyleName("nonowner");
        billableHoursLbl = new ELabel("", ContentMode.HTML).withStyleName("hint");
        nonBillableHoursLbl = new ELabel("", ContentMode.HTML).withStyleName("hint");
        helpBlock.with(assignMeLbl, assignOtherLbl, nonAssigneeLbl, nonAssigneeLbl, billableHoursLbl,
                nonBillableHoursLbl);

        noteContainer.with(helpBlock, calendar).expand(calendar);
        this.with(searchPanel, buildHeader(), noteContainer);
        displayMonthView();
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    private MHorizontalLayout buildHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withFullWidth();

        MHorizontalLayout headerLeftContainer = new MHorizontalLayout();
        MButton todayBtn = new MButton(AppContext.getMessage(DayI18nEnum.OPT_TODAY), clickEvent -> {
            baseDate = new LocalDate();
            displayCalendarView();
        }).withStyleName(UIConstants.BUTTON_ACTION);
        todayBtn.setStyleName(UIConstants.BUTTON_ACTION);
        ButtonGroup navigationBtns = new ButtonGroup();
        MButton previousBtn = new MButton("", clickEvent -> {
            if (mode == CalendarMode.DAILY) {
                baseDate = baseDate.minusDays(1);
            } else if (mode == CalendarMode.WEEKLY) {
                baseDate = baseDate.minusWeeks(1);
            } else {
                baseDate = baseDate.minusMonths(1);
            }
            displayCalendarView();
        }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.CHEVRON_LEFT);
        navigationBtns.addButton(previousBtn);

        MButton nextBtn = new MButton("", clickEvent -> {
            if (mode == CalendarMode.DAILY) {
                baseDate = baseDate.plusDays(1);
            } else if (mode == CalendarMode.WEEKLY) {
                baseDate = baseDate.plusWeeks(1);
            } else {
                baseDate = baseDate.plusMonths(1);
            }
            displayCalendarView();
        }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.CHEVRON_RIGHT);
        navigationBtns.addButton(nextBtn);

        headerLeftContainer.with(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        CssLayout titleWrapper = new CssLayout();
        headerLbl = ELabel.h2("");
        titleWrapper.addComponent(headerLbl);

        MButton dailyBtn = new MButton(AppContext.getMessage(DayI18nEnum.OPT_DAILY), clickEvent -> {
            baseDate = new LocalDate();
            displayDayView();
        }).withWidth("80px");

        MButton weeklyBtn = new MButton(AppContext.getMessage(DayI18nEnum.OPT_WEEKLY), clickEvent -> {
            baseDate = new LocalDate();
            displayWeekView();
        }).withWidth("80px");

        MButton monthlyBtn = new MButton(AppContext.getMessage(DayI18nEnum.OPT_MONTHLY), clickEvent -> {
            baseDate = new LocalDate();
            displayMonthView();
        }).withWidth("80px");

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(dailyBtn);
        viewButtons.addButton(weeklyBtn);
        viewButtons.addButton(monthlyBtn);
        viewButtons.withDefaultButton(monthlyBtn);

        MHorizontalLayout rightControls = new MHorizontalLayout().with(viewButtons);

        header.with(titleWrapper, rightControls).withAlign(titleWrapper, Alignment.MIDDLE_CENTER).withAlign(rightControls,
                Alignment.MIDDLE_RIGHT);
        return header;
    }

    @Override
    public void queryAssignments(ProjectGenericTaskSearchCriteria criteria) {
        searchCriteria = criteria;
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        RangeDateSearchField dateRange = new RangeDateSearchField(startDate.toDate(), endDate.toDate());
        searchCriteria.setDateInRange(dateRange);
        loadEvents();
    }

    private void loadEventViews() {
        calendar.setStartDate(startDate.toDate());
        calendar.setEndDate(endDate.toDate());
        RangeDateSearchField dateRange = new RangeDateSearchField(startDate.toDate(), endDate.toDate());
        searchCriteria.setDateInRange(dateRange);
        loadEvents();
    }

    private void displayCalendarView() {
        if (mode == CalendarMode.DAILY) {
            displayDayView();
        } else if (mode == CalendarMode.WEEKLY) {
            displayWeekView();
        } else {
            displayMonthView();
        }
    }

    private void loadEvents() {
        final GenericAssignmentProvider provider = new GenericAssignmentProvider();
        provider.addEventSetChangeListener(new CalendarEventProvider.EventSetChangeListener() {
            @Override
            public void eventSetChange(CalendarEventProvider.EventSetChangeEvent event) {
                assignMeLbl.setValue("Assign to me (" + provider.getAssignMeNum() + ")");
                assignOtherLbl.setValue("Assign to others (" + provider.getAssignOthersNum() + ")");
                nonAssigneeLbl.setValue("Not assign (" + provider.getNotAssignNum() + ")");
                billableHoursLbl.setValue(FontAwesome.MONEY.getHtml() + " " + AppContext.getMessage
                        (TimeTrackingI18nEnum.OPT_BILLABLE_HOURS_VALUE, provider.getTotalBillableHours()));
                nonBillableHoursLbl.setValue(FontAwesome.GIFT.getHtml() + " " + AppContext.getMessage
                        (TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS_VALUE, provider.getTotalNonBillableHours()));
            }
        });
        provider.loadEvents(searchCriteria, false);
        calendar.setEventProvider(provider);
        calendar.markAsDirtyRecursive();
    }

    private void displayDayView() {
        mode = CalendarMode.DAILY;
        headerLbl.setValue(baseDate.toString(DAY_FORMATTER));
        startDate = baseDate;
        endDate = baseDate;
        loadEventViews();
    }

    private void displayWeekView() {
        mode = CalendarMode.WEEKLY;
        startDate = baseDate.dayOfWeek().withMinimumValue();
        endDate = baseDate.dayOfWeek().withMaximumValue();
        headerLbl.setValue(startDate.toString(WEEK_FORMATTER) + " - " + endDate.toString(WEEK_FORMATTER));
        loadEventViews();
    }

    private void displayMonthView() {
        mode = CalendarMode.MONTHLY;
        startDate = baseDate.dayOfMonth().withMinimumValue();
        endDate = baseDate.dayOfMonth().withMaximumValue();
        headerLbl.setValue(baseDate.toString(MONTH_FORMATTER));
        loadEventViews();
    }

    @Override
    public HasSearchHandlers<ProjectGenericTaskSearchCriteria> getSearchHandlers() {
        return searchPanel;
    }
}
