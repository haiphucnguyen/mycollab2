package com.esofthead.mycollab.core.schedule.recurring;

public class YearlyEventFollowAdvanceSettingMonth implements RecurringEvent {
	private int happenIn;
	private String kindOfDay;
	private String month;

	public int getHappenIn() {
		return happenIn;
	}

	public void setHappenIn(int happenIn) {
		this.happenIn = happenIn;
	}

	public String getKindOfDay() {
		return kindOfDay;
	}

	public void setKindOfDay(String kindOfDay) {
		this.kindOfDay = kindOfDay;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
