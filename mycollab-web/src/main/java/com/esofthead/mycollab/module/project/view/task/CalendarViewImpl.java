package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.i18n.TaskGroupI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Label;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.vaadin.peter.buttongroup.ButtonGroup;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
@ViewComponent
public class CalendarViewImpl extends AbstractPageView implements CalendarView {
    private static final int MONTH_VIEW = 1;

    private static final DateTimeFormatter MY_FORMATTER = DateTimeFormat.forPattern("MMMM, yyyy");
    private static final DateTimeFormatter DMY_FORMATTER = DateTimeFormat.forPattern("d MMMM, yyyy");

    private Label headerLbl;
    private Calendar calendar;
    private LocalDate baseDate;

    public CalendarViewImpl() {
        this.withMargin(true).withSpacing(true);
        baseDate = new LocalDate();
    }

    @Override
    public void display() {
        calendar = new Calendar();
        calendar.addStyleName("assignment-calendar");
        calendar.setWidth("100%");
        calendar.setHeight("800px");
        this.with(buildHeader(), calendar);
        displayMonthView();
    }

    private MHorizontalLayout buildHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");

        MHorizontalLayout headerLeftContainer = new MHorizontalLayout();
        Button todayBtn = new Button("Today", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = new LocalDate();
                displayMonthView();
            }
        });
        todayBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        ButtonGroup navigationBtns = new ButtonGroup();
        navigationBtns.setStyleName("navigation-btns");
        Button previousBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = baseDate.minusMonths(1);
                displayMonthView();
            }
        });
        previousBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        previousBtn.setIcon(FontAwesome.CHEVRON_LEFT);
        navigationBtns.addButton(previousBtn);

        Button nextBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                baseDate = baseDate.plusMonths(1);
                displayMonthView();
            }
        });
        nextBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        nextBtn.setIcon(FontAwesome.CHEVRON_RIGHT);
        navigationBtns.addButton(nextBtn);

        headerLeftContainer.with(todayBtn, navigationBtns);
        header.with(headerLeftContainer).withAlign(headerLeftContainer, Alignment.MIDDLE_LEFT);

        MHorizontalLayout rightHeaderContainer = new MHorizontalLayout();
        headerLbl = new Label();
        headerLbl.setStyleName("h2");
        rightHeaderContainer.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        Button advanceDisplayBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new TaskEvent.GotoDashboard(CalendarViewImpl.this, null));
            }
        });
        advanceDisplayBtn.setIcon(FontAwesome.SITEMAP);
        advanceDisplayBtn.setDescription(AppContext.getMessage(TaskGroupI18nEnum.ADVANCED_VIEW_TOOLTIP));

        Button calendarBtn = new Button();
        calendarBtn.setDescription("Calendar View");
        calendarBtn.setIcon(FontAwesome.CALENDAR);

        Button chartDisplayBtn = new Button(null, new Button.ClickListener() {
            private static final long serialVersionUID = -5707546605789537298L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new TaskEvent.GotoGanttChart(CalendarViewImpl.this, null));
            }
        });
        chartDisplayBtn.setDescription("Display Gantt chart");
        chartDisplayBtn.setIcon(FontAwesome.BAR_CHART_O);

        Button kanbanBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new TaskEvent.GotoKanbanView(CalendarViewImpl.this, null));
            }
        });
        kanbanBtn.setDescription("Kanban View");
        kanbanBtn.setIcon(FontAwesome.TH);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(calendarBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.addButton(chartDisplayBtn);
        viewButtons.setDefaultButton(calendarBtn);

        rightHeaderContainer.with(headerLbl, viewButtons);
        header.with(rightHeaderContainer).withAlign(rightHeaderContainer, Alignment.MIDDLE_RIGHT);
        return header;
    }

    private void displayMonthView() {
        LocalDate firstDayOfMonth = baseDate.dayOfMonth().withMinimumValue();
        LocalDate lastDayOfMonth = baseDate.dayOfMonth().withMaximumValue();
        calendar.setStartDate(firstDayOfMonth.toDate());
        calendar.setEndDate(lastDayOfMonth.toDate());
        headerLbl.setValue(baseDate.toString(MY_FORMATTER));
    }
}
