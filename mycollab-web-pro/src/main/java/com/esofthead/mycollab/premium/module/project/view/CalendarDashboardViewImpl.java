package com.esofthead.mycollab.premium.module.project.view;

import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ICalendarDashboardView;
import com.esofthead.mycollab.module.project.view.task.calendar.GenericTaskEvent;
import com.esofthead.mycollab.premium.module.project.view.task.AgreegateGenericCalendarProvider;
import com.esofthead.mycollab.premium.module.project.view.task.TaskWithProjectAddWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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

    public CalendarDashboardViewImpl() {
        baseDate = new LocalDate();
        this.withMargin(true);
    }

    @Override
    public void display() {
        this.removeAllComponents();
        ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        projectKeys = projectService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        calendar = new Calendar();
        calendar.addStyleName("assignment-calendar");
        calendar.setWidth("100%");
        calendar.setHeight("100%");
        calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
            @Override
            public void eventClick(CalendarComponentEvents.EventClick event) {
                GenericTaskEvent calendarEvent = (GenericTaskEvent) event.getCalendarEvent();
                SimpleTask task = calendarEvent.getAssignment();
                UI.getCurrent().addWindow(new TaskWithProjectAddWindow(task));
            }
        });

        calendar.setHandler(new CalendarComponentEvents.DateClickHandler() {
            @Override
            public void dateClick(CalendarComponentEvents.DateClickEvent dateClickEvent) {
                SimpleTask task = new SimpleTask();
                task.setStartdate(dateClickEvent.getDate());
                task.setEnddate(dateClickEvent.getDate());
                UI.getCurrent().addWindow(new TaskWithProjectAddWindow(task));
            }
        });

        calendar.setHandler(new BasicEventMoveHandler() {
            @Override
            public void eventMove(CalendarComponentEvents.MoveEvent event) {

            }
        });

        calendar.setHandler(new BasicEventResizeHandler() {
            @Override
            public void eventResize(CalendarComponentEvents.EventResize event) {
               
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
        todayBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        ButtonGroup navigationBtns = new ButtonGroup();
        navigationBtns.setStyleName("navigation-btns");
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
        previousBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
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
        nextBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        nextBtn.setIcon(FontAwesome.CHEVRON_RIGHT);
        navigationBtns.addButton(nextBtn);

        headerLeftContainer.with(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        CssLayout titleWrapper = new CssLayout();
        headerLbl = new Label();
        headerLbl.setStyleName("h1");
        titleWrapper.addComponent(headerLbl);

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

        header.with(titleWrapper, viewButtons).withAlign(titleWrapper, Alignment.MIDDLE_CENTER).withAlign
                (viewButtons, Alignment.MIDDLE_RIGHT);
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
        final AgreegateGenericCalendarProvider provider = new AgreegateGenericCalendarProvider();
        provider.addEventSetChangeListener(new CalendarEventProvider.EventSetChangeListener() {
            @Override
            public void eventSetChange(CalendarEventProvider.EventSetChangeEvent event) {
                assignMeLbl.setValue("Assign to me (" + provider.getAssignMeNum() + ")");
                assignOtherLbl.setValue("Assign to others (" + provider.getAssignOthersNum() + ")");
                nonAssigneeLbl.setValue("Not be assigned (" + provider.getNotAssignNum() + ")");
                billableHoursLbl.setValue(FontAwesome.MONEY.getHtml() + " Billable hours: " + provider
                        .getTotalBillableHours());
                nonBillableHoursLbl.setValue(FontAwesome.GIFT.getHtml() + " Non billable hours: " + provider
                        .getTotalNonBillableHours());
            }
        });
        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        CollectionValueSearchField projectIdsInList = new CollectionValueSearchField(SearchField.AND,
                "m_prj_task.projectid IN", projectKeys);
        searchCriteria.addExtraField(projectIdsInList);
        CompositionSearchField compoField = new CompositionSearchField(SearchField.AND);
        compoField.addField(new BetweenValuesSearchField("", "m_prj_task.startdate BETWEEN ", start.toDate(), end.toDate()));
        compoField.addField(new BetweenValuesSearchField("", "m_prj_task.enddate BETWEEN ", start.toDate(), end.toDate()));
        searchCriteria.addExtraField(compoField);

        provider.loadEvents(searchCriteria);
        calendar.setEventProvider(provider);
    }
}
