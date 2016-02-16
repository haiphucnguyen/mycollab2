package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.AssignmentEvent;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ICalendarDashboardView;
import com.esofthead.mycollab.pro.module.project.view.assignments.GenericAssignmentEvent;
import com.esofthead.mycollab.pro.module.project.view.assignments.GenericAssignmentProvider;
import com.esofthead.mycollab.module.project.view.bug.BugAddWindow;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.esofthead.mycollab.module.project.view.task.TaskAddWindow;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.pro.module.project.ui.components.EntityWithProjectAddHandler;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
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
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.vaadin.peter.buttongroup.ButtonGroup;
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
    private LocalDate baseDate;
    private List<Integer> projectKeys;
    private boolean isMonthView = true;

    private ApplicationEventListener<AssignmentEvent.NewAssignmentAdd> taskChangeHandler = new ApplicationEventListener<AssignmentEvent.NewAssignmentAdd>() {
        @Override
        @Subscribe
        public void handle(AssignmentEvent.NewAssignmentAdd event) {
            String type = event.getTypeVal();
            Integer typeId = event.getTypeIdVal();
            ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setTypeIds(new SetSearchField<>(typeId));
            searchCriteria.setTypes(new SetSearchField<>(type));
            ProjectGenericTaskService assignmentService = ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class);
            List<ProjectGenericTask> assignments = assignmentService.findPagableListByCriteria(new SearchRequest<>(searchCriteria));
            GenericAssignmentProvider provider = (GenericAssignmentProvider) calendar.getEventProvider();
            for (ProjectGenericTask assignment : assignments) {
                GenericAssignmentEvent assignmentEvent = new GenericAssignmentEvent(assignment);
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
        baseDate = new LocalDate();
        ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        projectKeys = projectService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        calendar = new Calendar();
        calendar.addStyleName("assignment-calendar");
        calendar.setWidth("100%");
        calendar.setHeight("100%");
        calendar.setEventCaptionAsHtml(true);
        calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
            @Override
            public void eventClick(CalendarComponentEvents.EventClick event) {
                GenericAssignmentEvent calendarEvent = (GenericAssignmentEvent) event.getCalendarEvent();
                ProjectGenericTask assignment = calendarEvent.getAssignment();
                if (ProjectTypeConstants.TASK.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                    ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                    SimpleTask task = taskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new TaskAddWindow(task));
                } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
                    MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
                    SimpleMilestone milestone = milestoneService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
                } else if (ProjectTypeConstants.BUG.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
                    BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                    SimpleBug bug = bugService.findById(assignment.getTypeId(), AppContext.getAccountId());
                    UI.getCurrent().addWindow(new BugAddWindow(bug));
                } else if (ProjectTypeConstants.RISK.equals(assignment.getType()) &&
                        CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {

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
                .withWidth("100%");
        MVerticalLayout helpBlock = new MVerticalLayout().withMargin(false);
        assignMeLbl = new ELabel("").withStyleName("owner").withWidth("200px");
        assignOtherLbl = new ELabel("").withStyleName("otheruser").withWidth("200px");
        nonAssigneeLbl = new ELabel("").withStyleName("nonowner").withWidth("200px");
        helpBlock.with(assignMeLbl, assignOtherLbl, nonAssigneeLbl);
        billableHoursLbl = new Label("", ContentMode.HTML);
        nonBillableHoursLbl = new Label("", ContentMode.HTML);
        MVerticalLayout hoursNoteContainer = new MVerticalLayout().withMargin(false);
        hoursNoteContainer.with(billableHoursLbl, nonBillableHoursLbl);
        hoursNoteContainer.setWidthUndefined();
        noteContainer.with(helpBlock, hoursNoteContainer).withAlign(helpBlock, Alignment.TOP_LEFT)
                .withAlign(hoursNoteContainer, Alignment.TOP_RIGHT);
        this.with(buildHeader(), noteContainer, calendar);
        displayMonthView();
    }

    private MHorizontalLayout buildHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");

        MHorizontalLayout headerLeftContainer = new MHorizontalLayout();
        Button todayBtn = new Button("Today", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = new LocalDate();
                displayCalendar();
            }
        });
        todayBtn.setStyleName(UIConstants.BUTTON_ACTION);
        ButtonGroup navigationBtns = new ButtonGroup();
        Button previousBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (isMonthView) {
                    baseDate = baseDate.minusMonths(1);
                } else {
                    baseDate = baseDate.minusWeeks(1);
                }

                displayCalendar();
            }
        });
        previousBtn.setStyleName(UIConstants.BUTTON_ACTION);
        previousBtn.setIcon(FontAwesome.CHEVRON_LEFT);
        navigationBtns.addButton(previousBtn);

        Button nextBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (isMonthView) {
                    baseDate = baseDate.plusMonths(1);
                } else {
                    baseDate = baseDate.plusWeeks(1);
                }
                displayCalendar();
            }
        });
        nextBtn.setStyleName(UIConstants.BUTTON_ACTION);
        nextBtn.setIcon(FontAwesome.CHEVRON_RIGHT);
        navigationBtns.addButton(nextBtn);

        headerLeftContainer.with(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        CssLayout titleWrapper = new CssLayout();
        headerLbl = new Label();
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        titleWrapper.addComponent(headerLbl);

        Button newTaskBtn = new Button(AppContext.getMessage(TaskI18nEnum.BUTTON_NEW_TASK), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(new EntityWithProjectAddHandler().buildWindow(new SimpleTask()));
            }
        });
        newTaskBtn.setStyleName(UIConstants.BUTTON_ACTION);
        final ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        final Button weekViewBtn = new Button("Week");
        weekViewBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                displayWeekView();
                viewButtons.setDefaultButton(weekViewBtn);
            }
        });
        final Button monthViewBtn = new Button("Month");
        monthViewBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                displayMonthView();
                viewButtons.setDefaultButton(monthViewBtn);
            }
        });
        viewButtons.addButton(weekViewBtn);
        viewButtons.addButton(monthViewBtn);
        viewButtons.setDefaultButton(monthViewBtn);

        header.with(titleWrapper, viewButtons).withAlign(titleWrapper, Alignment.MIDDLE_CENTER)
                .withAlign(viewButtons, Alignment.MIDDLE_RIGHT);
        return header;
    }

    private void displayMonthView() {
        isMonthView = true;
        LocalDate firstDayOfMonth = baseDate.dayOfMonth().withMinimumValue();
        LocalDate lastDayOfMonth = baseDate.dayOfMonth().withMaximumValue();
        headerLbl.setValue(baseDate.toString(MY_FORMATTER));
        displayCalendarView(firstDayOfMonth, lastDayOfMonth);
    }

    private void displayWeekView() {
        isMonthView = false;
        LocalDate firstDayOfWeek = baseDate.dayOfWeek().withMinimumValue();
        LocalDate lastDayOfWeek = baseDate.dayOfWeek().withMaximumValue();
        headerLbl.setValue(firstDayOfWeek.toString(DMY_FORMATTER) + " - " + lastDayOfWeek.toString(DMY_FORMATTER));
        displayCalendarView(firstDayOfWeek, lastDayOfWeek);
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

    private void displayCalendarView(LocalDate start, LocalDate end) {
        calendar.setStartDate(start.toDate());
        calendar.setEndDate(end.toDate());
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
        final GenericAssignmentProvider provider = new GenericAssignmentProvider();
        provider.addEventSetChangeListener(new CalendarEventProvider.EventSetChangeListener() {
            @Override
            public void eventSetChange(CalendarEventProvider.EventSetChangeEvent event) {
                displayInfo(provider);
            }
        });
        if (CollectionUtils.isNotEmpty(projectKeys)) {
            provider.loadEvents(start.toDate(), end.toDate(), projectKeys);
        } else {
            displayInfo(provider);
        }
        calendar.setEventProvider(provider);
    }

    private void displayInfo(GenericAssignmentProvider provider) {
        assignMeLbl.setValue("Assign to me (" + provider.getAssignMeNum() + ")");
        assignOtherLbl.setValue("Assign to others (" + provider.getAssignOthersNum() + ")");
        nonAssigneeLbl.setValue("Not assign (" + provider.getNotAssignNum() + ")");
        billableHoursLbl.setValue(FontAwesome.MONEY.getHtml() + " Billable hours: " + provider
                .getTotalBillableHours());
        nonBillableHoursLbl.setValue(FontAwesome.GIFT.getHtml() + " Non billable hours: " + provider
                .getTotalNonBillableHours());
    }
}
