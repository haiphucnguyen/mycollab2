package com.esofthead.mycollab.core.schedule.recurring;

public class DailyEvent implements RecurringEvent {
	private int repeatInDay;

	public int getRepeatInDay() {
		return repeatInDay;
	}

	public void setRepeatInDay(int repeatInDay) {
		this.repeatInDay = repeatInDay;
	}
}
