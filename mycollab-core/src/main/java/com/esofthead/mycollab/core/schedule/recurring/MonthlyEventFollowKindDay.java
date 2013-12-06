package com.esofthead.mycollab.core.schedule.recurring;

public class MonthlyEventFollowKindDay implements RecurringEvent {
	private int happenIn;
	private String kindOfDay;
	private int monthStep;

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

	public int getMonthStep() {
		return monthStep;
	}

	public void setMonthStep(int monthStep) {
		this.monthStep = monthStep;
	}

}
