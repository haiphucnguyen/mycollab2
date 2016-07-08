package com.mycollab.pro.module.project.view;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.db.arguments.RangeDateSearchField;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.ProjectPermissionChecker;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.events.AssignmentEvent;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.*;
import com.mycollab.module.project.view.ICalendarDashboardView;
import com.mycollab.module.project.view.bug.BugAddWindow;
import com.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.mycollab.module.project.view.task.TaskAddWindow;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.ui.components.EntityWithProjectAddHandler;
import com.mycollab.pro.module.project.view.assignments.CalendarSearchPanel;
import com.mycollab.pro.module.project.view.assignments.GenericAssignmentEvent;
import com.mycollab.pro.module.project.view.assignments.GenericAssignmentProvider;
import com.mycollab.pro.module.project.view.risk.RiskAddWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
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
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
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
 * @since 5.2.1
 */
@ViewComponent
public class CalendarDashboardViewImpl extends AbstractPageView implements ICalendarDashboardView {
    private static final DateTimeFormatter MY_FORMATTER = DateTimeFormat.forPattern("MMMM, yyyy");
    private static final DateTimeFormatter DMY_FORMATTER = DateTimeFormat.forPattern("dd MMMM, yyyy");

    private Label headerLbl, billableHoursLbl, nonBillableHoursLbl, assignMeLbl, assignOtherLbl, nonAssigneeLbl;
    private Calendar calendar;
    private LocalDate baseDate, startDate, endDate;
    private List<Integer> projectKeys;
    private boolean isMonthView = true;
    private ProjectGenericTaskSearchCriteria searchCriteria;
    private CalendarSearchPanel searchPanel;

    private ApplicationEventListener<AssignmentEvent.NewAssignmentAdd> taskChangeHandler = new ApplicationEventListener<AssignmentEvent.NewAssignmentAdd>() {
        @Override
        @Subscribe
        public void handle(AssignmentEvent.NewAssignmentAdd event) {
            String type = event.getTypeVal();
            Integer typeId = event.getTypeIdVal();
            ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setTypeIds(new SetSearchField<>(typeId));
            searchCriteria.setTypes(new SetSearchField<>(type));
            ProjectGenericTaskService assignmentService = AppContextUtil.getSpringBean(ProjectGenericTaskService.class);
            List<ProjectGenericTask> assignments = assignmentService.findPagableListByCriteria(new BasicSearchRequest<>(searchCriteria));
            GenericAssignmentProvider provider = (GenericAssignmentProvider) calendar.getEventProvider();
            for (ProjectGenericTask assignment : assignments) {
                GenericAssignmentEvent assignmentEvent = new GenericAssignmentEvent(assignment, true);
                if (provider.containsEvent(assignmentEvent)) {
                    provider.removeEvent(assignmentEvent);
                    provider.addEvent(assignmentEvent);
                } else {
                    provider.addEvent(assignmentEvent);
                }
            }
        }
    };

    public CalendarDashboardViewImpl() {
        this.withMargin(true);
    }

    @Override
    public void initContent() {
        baseDate = new LocalDate();
        searchPanel = new CalendarSearchPanel(false);
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(taskChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(taskChangeHandler);
        super.detach();
    }

    @Override
    public void display() {
        this.removeAllComponents();
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        projectKeys = projectService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(projectKeys));
        calendar = new Calendar();
        calendar.addStyleName("assignment-calendar");
        calendar.setWidth("100%");
        calendar.setHeight("100%");
        calendar.setEventCaptionAsHtml(true);
        calendar.setHandler(new CalendarComponentEvents.BackwardHandler() {
            @Override
            public void backward(CalendarComponentEvents.BackwardEvent backwardEvent) {
                if (!isMonthView) {
                    baseDate = baseDate.minusWeeks(1);
                    displayWeekView();
                }
            }
        });
        calendar.setHandler(new CalendarComponentEvents.ForwardHandler() {
            @Override
            public void forward(CalendarComponentEvents.ForwardEvent forwardEvent) {
                if (!isMonthView) {
                    baseDate = baseDate.plusWeeks(1);
                    displayWeekView();
                }
            }
        });
        calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
            @Override
            public void eventClick(CalendarComponentEvents.EventClick event) {
                GenericAssignmentEvent calendarEvent = (GenericAssignmentEvent) event.getCalendarEvent();
                ProjectGenericTask assignment = calendarEvent.getAssignment();
                if (ProjectTypeConstants.TASK.equals(assignment.getType()) &&
                        ProjectPermissionChecker.canWrite(assignment.getProjectId(), ProjectRolePermissionCollections.TASKS)) {
                    ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                    SimpleTask task = taskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new TaskAddWindow(task));
                } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType()) &&
                        ProjectPermissionChecker.canWrite(assignment.getProjectId(), ProjectRolePermissionCollections.MILESTONES)) {
                    MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                    SimpleMilestone milestone = milestoneService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
                } else if (ProjectTypeConstants.BUG.equals(assignment.getType()) &&
                        ProjectPermissionChecker.canWrite(assignment.getProjectId(), ProjectRolePermissionCollections.BUGS)) {
                    BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                    SimpleBug bug = bugService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new BugAddWindow(bug));
                } else if (ProjectTypeConstants.RISK.equals(assignment.getType()) &&
                        ProjectPermissionChecker.canWrite(assignment.getProjectId(), ProjectRolePermissionCollections.RISKS)) {
                    RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                    SimpleRisk risk = riskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new RiskAddWindow(risk));
                }
            }
        });

        calendar.setHandler(new CalendarComponentEvents.DateClickHandler() {
            @Override
            public void dateClick(CalendarComponentEvents.DateClickEvent event) {
//                SimpleTask task = new SimpleTask();
//                task.setStartdate(dateClickEvent.getDate());
//                task.setEnddate(dateClickEvent.getDate());
//                UI.getCurrent().addWindow(new EntityWithProjectAddHandler().buildWindow(task));
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
        helpBlock.with(assignMeLbl, assignOtherLbl, nonAssigneeLbl, nonAssigneeLbl, billableHoursLbl, nonBillableHoursLbl);

        noteContainer.with(helpBlock, calendar).expand(calendar);
        this.with(searchPanel, buildHeader(), noteContainer);
        displayMonthView();
    }

    private MHorizontalLayout buildHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withFullWidth();

        MButton todayBtn = new MButton(AppContext.getMessage(DayI18nEnum.OPT_TODAY), clickEvent -> {
            baseDate = new LocalDate();
            displayCalendar();
        }).withStyleName(UIConstants.BUTTON_ACTION);
        ButtonGroup navigationBtns = new ButtonGroup();
        MButton previousBtn = new MButton("", clickEvent -> {
            if (isMonthView) {
                baseDate = baseDate.minusMonths(1);
            } else {
                baseDate = baseDate.minusWeeks(1);
            }
            displayCalendar();
        }).withIcon(FontAwesome.CHEVRON_LEFT).withStyleName(UIConstants.BUTTON_ACTION);
        navigationBtns.addButton(previousBtn);

        MButton nextBtn = new MButton("", clickEvent -> {
            if (isMonthView) {
                baseDate = baseDate.plusMonths(1);
            } else {
                baseDate = baseDate.plusWeeks(1);
            }
            displayCalendar();
        }).withIcon(FontAwesome.CHEVRON_RIGHT).withStyleName(UIConstants.BUTTON_ACTION);
        navigationBtns.addButton(nextBtn);

        MHorizontalLayout headerLeftContainer = new MHorizontalLayout(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        CssLayout titleWrapper = new CssLayout();
        headerLbl = new Label();
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        titleWrapper.addComponent(headerLbl);

        MButton newTaskBtn = new MButton(AppContext.getMessage(TaskI18nEnum.NEW),
                clickEvent -> UI.getCurrent().addWindow(new EntityWithProjectAddHandler().buildWindow(new SimpleTask())))
                .withStyleName(UIConstants.BUTTON_ACTION);
        final ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        final Button weekViewBtn = new Button(AppContext.getMessage(DayI18nEnum.OPT_WEEK));
        weekViewBtn.addClickListener(clickEvent -> {
            displayWeekView();
            viewButtons.withDefaultButton(weekViewBtn);
        });
        final Button monthViewBtn = new Button(AppContext.getMessage(DayI18nEnum.OPT_MONTH));
        monthViewBtn.addClickListener(clickEvent -> {
            displayMonthView();
            viewButtons.withDefaultButton(monthViewBtn);
        });
        viewButtons.addButton(weekViewBtn);
        viewButtons.addButton(monthViewBtn);
        viewButtons.withDefaultButton(monthViewBtn);

        header.with(titleWrapper, viewButtons).withAlign(titleWrapper, Alignment.MIDDLE_CENTER)
                .withAlign(viewButtons, Alignment.MIDDLE_RIGHT);
        return header;
    }

    private void displayMonthView() {
        isMonthView = true;
        startDate = baseDate.dayOfMonth().withMinimumValue();
        endDate = baseDate.dayOfMonth().withMaximumValue();
        headerLbl.setValue(baseDate.toString(MY_FORMATTER));
        displayCalendarView();
    }

    private void displayWeekView() {
        isMonthView = false;
        startDate = baseDate.dayOfWeek().withMinimumValue();
        endDate = baseDate.dayOfWeek().withMaximumValue();
        headerLbl.setValue(startDate.toString(DMY_FORMATTER) + " - " + endDate.toString(DMY_FORMATTER));
        displayCalendarView();
        calendar.setFirstVisibleHourOfDay(0);
        calendar.setLastVisibleHourOfDay(0);
    }

    private void displayCalendar() {
        if (isMonthView) {
            displayMonthView();
        } else {
            displayWeekView();
        }
    }

    private void displayCalendarView() {
        calendar.setStartDate(startDate.toDate());
        calendar.setEndDate(endDate.toDate());

        final GenericAssignmentProvider provider = new GenericAssignmentProvider();
        provider.addEventSetChangeListener(eventSetChangeEvent -> displayInfo(provider));
        if (CollectionUtils.isNotEmpty(projectKeys)) {
            RangeDateSearchField dateRange = new RangeDateSearchField(startDate.toDate(), endDate.toDate());
            searchCriteria.setDateInRange(dateRange);
            provider.loadEvents(searchCriteria, true);
        } else {
            displayInfo(provider);
        }
        calendar.setEventProvider(provider);
        calendar.markAsDirtyRecursive();
    }

    @Override
    public void queryAssignments(ProjectGenericTaskSearchCriteria criteria) {
        searchCriteria = criteria;
        searchCriteria.setProjectIds(new SetSearchField<>(projectKeys));
        RangeDateSearchField dateRange = new RangeDateSearchField(startDate.toDate(), endDate.toDate());
        searchCriteria.setDateInRange(dateRange);
        displayCalendarView();
    }

    private void displayInfo(GenericAssignmentProvider provider) {
        assignMeLbl.setValue("Assign to me (" + provider.getAssignMeNum() + ")");
        assignOtherLbl.setValue("Assign to others (" + provider.getAssignOthersNum() + ")");
        nonAssigneeLbl.setValue("Not assign (" + provider.getNotAssignNum() + ")");
        billableHoursLbl.setValue(FontAwesome.MONEY.getHtml() + " " + AppContext.getMessage(TimeTrackingI18nEnum
                .OPT_BILLABLE_HOURS_VALUE, provider.getTotalBillableHours()));
        nonBillableHoursLbl.setValue(FontAwesome.GIFT.getHtml() + " " + AppContext.getMessage(TimeTrackingI18nEnum
                .OPT_NON_BILLABLE_HOURS_VALUE, provider.getTotalNonBillableHours()));
    }

    @Override
    public HasSearchHandlers<ProjectGenericTaskSearchCriteria> getSearchHandlers() {
        return searchPanel;
    }
}