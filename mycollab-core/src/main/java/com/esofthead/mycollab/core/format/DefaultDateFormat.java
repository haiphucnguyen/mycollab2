package com.esofthead.mycollab.core.format;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 *
 */
public class DefaultDateFormat implements IDateFormat {

	@Override
	public String getShortDateFormat() {
		return "MM/dd/yy";
	}

	@Override
	public String getDateFormat() {
		return "MM/dd/yyyy";
	}

	@Override
	public String getDateTimeFormat() {
		return "MM/dd/yyyy hh:mm a";
	}

	@Override
	public String getDayMonthFormat() {
		return "MM/dd";
	}

}
