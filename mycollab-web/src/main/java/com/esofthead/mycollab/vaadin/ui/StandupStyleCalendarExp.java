/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.vaadin.risto.stylecalendar.DateOptionsGenerator;
import org.vaadin.risto.stylecalendar.StyleCalendar;

import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
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
		this.setHeight("170px");
		this.setSpacing(false);

		styleCalendar.setRenderHeader(false);
		styleCalendar.setRenderWeekNumbers(false);
		styleCalendar.setImmediate(true);
		styleCalendar.setWidth("100%");
		setDateOptionsGenerator();

		btnShowNextYear = new Button();
		btnShowNextYear.setIcon(MyCollabResource
				.newResource("icons/16/cal_year_next.png"));
		btnShowNextYear.setStyleName("link");

		btnShowNextMonth = new Button();
		btnShowNextMonth.setIcon(MyCollabResource
				.newResource("icons/16/cal_month_next.png"));
		btnShowNextMonth.setStyleName("link");

		btnShowPreviousMonth = new Button();
		btnShowPreviousMonth.setIcon(MyCollabResource
				.newResource("icons/16/cal_month_pre.png"));
		btnShowPreviousMonth.setStyleName("link");

		btnShowPreviousYear = new Button();
		btnShowPreviousYear.setIcon(MyCollabResource
				.newResource("icons/16/cal_year_pre.png"));
		btnShowPreviousYear.setStyleName("link");

		lbSelectedDate.setValue(AppContext.formatDate(new Date()));
		lbSelectedDate.addStyleName("calendarDateLabel");
		lbSelectedDate.setWidth("80");

		HorizontalLayout layoutControl = new HorizontalLayout();
		layoutControl.setStyleName("calendarHeader");
		layoutControl.setWidth("100%");
		layoutControl.setHeight("35px");

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
		this.setComponentAlignment(layoutControl, Alignment.TOP_CENTER);

		this.addComponent(styleCalendar);
		this.setExpandRatio(styleCalendar, 1.0f);
		this.setComponentAlignment(styleCalendar, Alignment.MIDDLE_CENTER);
	}

	public void setLabelTime(String date) {
		lbSelectedDate.setValue(date);
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

					if (dateEquals(date, redDate)
							&& dateIsTodayOrBefore(redDate)) {
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
