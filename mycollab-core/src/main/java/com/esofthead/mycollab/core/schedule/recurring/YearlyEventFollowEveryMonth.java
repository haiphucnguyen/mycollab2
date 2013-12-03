package com.esofthead.mycollab.core.schedule.recurring;

public class YearlyEventFollowEveryMonth implements RecurringEvent {
	private String month;
	private int day;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
