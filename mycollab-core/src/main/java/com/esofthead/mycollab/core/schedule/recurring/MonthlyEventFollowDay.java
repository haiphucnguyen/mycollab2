package com.esofthead.mycollab.core.schedule.recurring;

public class MonthlyEventFollowDay implements RecurringEvent {
	private int numday;
	private int monthStep;

	public int getNumday() {
		return numday;
	}

	public void setNumday(int numday) {
		this.numday = numday;
	}

	public int getMonthStep() {
		return monthStep;
	}

	public void setMonthStep(int monthStep) {
		this.monthStep = monthStep;
	}

}
