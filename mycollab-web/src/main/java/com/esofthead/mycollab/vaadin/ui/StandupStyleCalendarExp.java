package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.vaadin.risto.stylecalendar.DateOptionsGenerator;
import org.vaadin.risto.stylecalendar.StyleCalendar;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class StandupStyleCalendarExp extends VerticalLayout {
	private List<Date> selectedDateList = new ArrayList<Date>();
	private StyleCalendar styleCalendar = new StyleCalendar();

	private Button btnShowNextMonth;
	private Button btnShowNextYear;
	private Button btnShowPreviousMonth;
	private Button btnShowPreviousYear;
	private Label lbSelectedDate = new Label();

	public StandupStyleCalendarExp() {
		this.setWidth("230px");
		this.setHeight("200px");

		styleCalendar.setRenderHeader(false);
		styleCalendar.setRenderWeekNumbers(false);
		styleCalendar.setImmediate(true);
		setDateOptionsGenerator();

		btnShowNextYear = createButtonControl(">>");
		btnShowNextMonth = createButtonControl(">");
		btnShowPreviousMonth = createButtonControl("<");
		btnShowPreviousYear = createButtonControl("<<");
		lbSelectedDate.setValue(AppContext.formatDate(new Date()));
		lbSelectedDate.setWidth("80");

		HorizontalLayout layoutControl = new HorizontalLayout();
		layoutControl.setWidth("100%");

		HorizontalLayout layoutButtonPrevious = new HorizontalLayout();
		layoutButtonPrevious.setSpacing(true);
		layoutButtonPrevious.addComponent(btnShowPreviousYear);
		layoutButtonPrevious.setComponentAlignment(btnShowPreviousYear,
				Alignment.MIDDLE_LEFT);
		layoutButtonPrevious.addComponent(btnShowPreviousMonth);
		layoutButtonPrevious.setComponentAlignment(btnShowPreviousMonth,
				Alignment.MIDDLE_LEFT);
		layoutControl.addComponent(layoutButtonPrevious);
		layoutControl.setComponentAlignment(layoutButtonPrevious,
				Alignment.MIDDLE_LEFT);

		layoutControl.addComponent(lbSelectedDate);
		layoutControl.setComponentAlignment(lbSelectedDate,
				Alignment.MIDDLE_CENTER);

		HorizontalLayout layoutButtonNext = new HorizontalLayout();
		layoutButtonNext.setSpacing(true);
		layoutButtonNext.addComponent(btnShowNextMonth);
		layoutButtonNext.setComponentAlignment(btnShowNextMonth,
				Alignment.MIDDLE_RIGHT);
		layoutButtonNext.addComponent(btnShowNextYear);
		layoutButtonNext.setComponentAlignment(btnShowNextYear,
				Alignment.MIDDLE_RIGHT);
		layoutControl.addComponent(layoutButtonNext);
		layoutControl.setComponentAlignment(layoutButtonNext,
				Alignment.MIDDLE_RIGHT);

		this.addComponent(layoutControl);
		this.setComponentAlignment(layoutControl, Alignment.MIDDLE_CENTER);

		this.addComponent(styleCalendar);
		this.setComponentAlignment(styleCalendar, Alignment.MIDDLE_CENTER);

	}

	public void setLabelTime(String date) {
		lbSelectedDate.setValue(date);
	}

	private Button createButtonControl(String caption) {
		Button btn = new Button(caption);
		btn.setWidth("24px");
		btn.setHeight("24px");
		return btn;
	}

	public void addSelectedDate(Date date) {
		if (date != null && !checkIfDateIsExistInSelectedDate(date)) {
			selectedDateList.add(date);
		}
	}

	private boolean checkIfDateIsExistInSelectedDate(Date date) {
		for (Date item : selectedDateList) {
			if (dateEquals(item, date)) {
				return true;
			}
		}
		return false;
	}

	private void setDateOptionsGenerator() {
		styleCalendar.setDateOptionsGenerator(new DateOptionsGenerator() {

			@Override
			public boolean isDateDisabled(Date date, StyleCalendar context) {
				return false;
			}

			@Override
			public String getTooltip(Date date, StyleCalendar context) {
				return null;
			}

			@Override
			public String getStyleName(Date date, StyleCalendar context) {
				for (Date redDate : selectedDateList) {
					Calendar c1 = Calendar.getInstance();
					c1.setTime(redDate);

					if (dateEquals(date, redDate) && dateIsTodayOrBefore(redDate)) {
						return "red";
					}
				}
				return null;
			}
		});
	}

	private boolean dateIsTodayOrBefore(Date date) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date);
		c2.setTime(styleCalendar.getShowingDate());

		return (c1.get(Calendar.DATE) <= c2
				.getActualMaximum(Calendar.DAY_OF_MONTH))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
	}

	private boolean dateEquals(Date first, Date second) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(first);
		c2.setTime(second);

		return ((c1.get(Calendar.DATE) == c2.get(Calendar.DATE))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) && (c1
				.get(Calendar.YEAR) == c2.get(Calendar.YEAR)));
	}

	public StyleCalendar getStyleCalendar() {
		return styleCalendar;
	}

	public Button getBtnShowNextMonth() {
		return btnShowNextMonth;
	}

	public Button getBtnShowNextYear() {
		return btnShowNextYear;
	}

	public Button getBtnShowPreviousMonth() {
		return btnShowPreviousMonth;
	}

	public Button getBtnShowPreviousYear() {
		return btnShowPreviousYear;
	}
}
