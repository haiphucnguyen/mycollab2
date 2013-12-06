package com.esofthead.mycollab.core.schedule.recurring;

public class WeeklyEvent implements RecurringEvent {
	private int repeatInWeekNum;

	private boolean happenInSunday;

	private boolean happenInMonday;
	private boolean happenInTuesday;
	private boolean happenInWednesday;
	private boolean happenInThursday;
	private boolean happenInFriday;
	private boolean happenInSaturday;

	public int getRepeatInWeekNum() {
		return repeatInWeekNum;
	}

	public void setRepeatInWeekNum(int repeatInWeekNum) {
		this.repeatInWeekNum = repeatInWeekNum;
	}

	public boolean isHappenInSunday() {
		return happenInSunday;
	}

	public void setHappenInSunday(boolean happenInSunday) {
		this.happenInSunday = happenInSunday;
	}

	public boolean isHappenInMonday() {
		return happenInMonday;
	}

	public void setHappenInMonday(boolean happenInMonday) {
		this.happenInMonday = happenInMonday;
	}

	public boolean isHappenInTuesday() {
		return happenInTuesday;
	}

	public void setHappenInTuesday(boolean happenInTuesday) {
		this.happenInTuesday = happenInTuesday;
	}

	public boolean isHappenInWednesday() {
		return happenInWednesday;
	}

	public void setHappenInWednesday(boolean happenInWednesday) {
		this.happenInWednesday = happenInWednesday;
	}

	public boolean isHappenInThursday() {
		return happenInThursday;
	}

	public void setHappenInThursday(boolean happenInThursday) {
		this.happenInThursday = happenInThursday;
	}

	public boolean isHappenInFriday() {
		return happenInFriday;
	}

	public void setHappenInFriday(boolean happenInFriday) {
		this.happenInFriday = happenInFriday;
	}

	public boolean isHappenInSaturday() {
		return happenInSaturday;
	}

	public void setHappenInSaturday(boolean happenInSaturday) {
		this.happenInSaturday = happenInSaturday;
	}

}
