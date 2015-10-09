package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
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
    private static final int WEEK_VIEW = 0;
    private static final int MONTH_VIEW = 1;

    private static final DateTimeFormatter MY_FORMATTER = DateTimeFormat.forPattern("MMMM, yyyy");
    private static final DateTimeFormatter DMY_FORMATTER = DateTimeFormat.forPattern("d MMMM, yyyy");

    private Label headerLbl;
    private Button switchViewBtn;
    private Calendar calendar;
    private int calendarView = WEEK_VIEW;
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
                if (calendarView == WEEK_VIEW) {
                    displayWeekView();
                } else {
                    displayMonthView();
                }
            }
        });
        todayBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        ButtonGroup navigationBtns = new ButtonGroup();
        navigationBtns.setStyleName("navigation-btns");
        Button previousBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (calendarView == WEEK_VIEW) {
                    baseDate = baseDate.minusDays(7);
                    displayWeekView();
                } else {
                    baseDate = baseDate.minusMonths(1);
                    displayMonthView();
                }
            }
        });
        previousBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        previousBtn.setIcon(FontAwesome.CHEVRON_LEFT);
        navigationBtns.addButton(previousBtn);

        Button nextBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (calendarView == WEEK_VIEW) {
                    baseDate = baseDate.plusDays(7);
                    displayWeekView();
                } else {
                    baseDate = baseDate.plusMonths(1);
                    displayMonthView();
                }
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
        switchViewBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (calendarView == WEEK_VIEW) {
                    displayMonthView();
                } else {
                    displayWeekView();
                }
            }
        });
        switchViewBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        if (calendarView == WEEK_VIEW) {
            switchViewBtn.setIcon(FontAwesome.TH);
        } else {
            switchViewBtn.setIcon(FontAwesome.NAVICON);
        }
        rightHeaderContainer.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        rightHeaderContainer.with(headerLbl, switchViewBtn);
        header.with(rightHeaderContainer).withAlign(rightHeaderContainer, Alignment.MIDDLE_RIGHT);
        return header;
    }

    private void displayMonthView() {
        calendarView = MONTH_VIEW;
        switchViewBtn.setIcon(FontAwesome.NAVICON);
        switchViewBtn.setDescription("Switch to week view");
        LocalDate firstDayOfMonth = baseDate.dayOfMonth().withMinimumValue();
        LocalDate lastDayOfMonth = baseDate.dayOfMonth().withMaximumValue();
        calendar.setStartDate(firstDayOfMonth.toDate());
        calendar.setEndDate(lastDayOfMonth.toDate());
        headerLbl.setValue(baseDate.toString(MY_FORMATTER));
    }

    private void displayWeekView() {
        calendarView = WEEK_VIEW;
        switchViewBtn.setIcon(FontAwesome.TH);
        switchViewBtn.setDescription("Switch to month view");
        LocalDate firstDayOfWeek = baseDate.dayOfWeek().withMinimumValue();
        LocalDate lastDayOfWeek = baseDate.dayOfWeek().withMaximumValue();
        calendar.setStartDate(firstDayOfWeek.toDate());
        calendar.setEndDate(lastDayOfWeek.toDate());
        calendar.setFirstVisibleHourOfDay(8);
        headerLbl.setValue(firstDayOfWeek.toString(DMY_FORMATTER) + " - " + lastDayOfWeek.toString(DMY_FORMATTER));
    }
}
