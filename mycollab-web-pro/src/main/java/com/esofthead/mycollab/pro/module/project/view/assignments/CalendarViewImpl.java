package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.AssignmentEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.assignments.CalendarView;
import com.esofthead.mycollab.module.project.view.bug.BugAddWindow;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.esofthead.mycollab.module.project.view.task.TaskAddWindow;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.pro.module.project.view.risk.RiskAddWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.esofthead.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
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
            List<ProjectGenericTask> assignments = assignmentService.findPagableListByCriteria(new BasicSearchRequest<>(searchCriteria));
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
        Button todayBtn = new Button("Today", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = new LocalDate();
                displayCalendarView();
            }
        });
        todayBtn.setStyleName(UIConstants.BUTTON_ACTION);
        ButtonGroup navigationBtns = new ButtonGroup();
        Button previousBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (mode == CalendarMode.DAILY) {
                    baseDate = baseDate.minusDays(1);
                } else if (mode == CalendarMode.WEEKLY) {
                    baseDate = baseDate.minusWeeks(1);
                } else {
                    baseDate = baseDate.minusMonths(1);
                }
                displayCalendarView();
            }
        });
        previousBtn.setStyleName(UIConstants.BUTTON_ACTION);
        previousBtn.setIcon(FontAwesome.CHEVRON_LEFT);
        navigationBtns.addButton(previousBtn);

        Button nextBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (mode == CalendarMode.DAILY) {
                    baseDate = baseDate.plusDays(1);
                } else if (mode == CalendarMode.WEEKLY) {
                    baseDate = baseDate.plusWeeks(1);
                } else {
                    baseDate = baseDate.plusMonths(1);
                }
                displayCalendarView();
            }
        });
        nextBtn.setStyleName(UIConstants.BUTTON_ACTION);
        nextBtn.setIcon(FontAwesome.CHEVRON_RIGHT);
        navigationBtns.addButton(nextBtn);

        headerLeftContainer.with(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        CssLayout titleWrapper = new CssLayout();
        headerLbl = ELabel.h2("");
        titleWrapper.addComponent(headerLbl);

        Button dailyBtn = new Button("Daily", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = new LocalDate();
                displayDayView();
            }
        });
        dailyBtn.setWidth("80px");

        Button weeklyBtn = new Button("Weekly", new Button.ClickListener() {
            private static final long serialVersionUID = -5707546605789537298L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                baseDate = new LocalDate();
                displayWeekView();
            }
        });
        weeklyBtn.setWidth("80px");

        Button monthlyBtn = new Button("Monthly", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = new LocalDate();
                displayMonthView();
            }
        });
        monthlyBtn.setWidth("80px");

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
                billableHoursLbl.setValue(FontAwesome.MONEY.getHtml() + " Billable hours: " + provider
                        .getTotalBillableHours());
                nonBillableHoursLbl.setValue(FontAwesome.GIFT.getHtml() + " Non billable hours: " + provider
                        .getTotalNonBillableHours());
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
