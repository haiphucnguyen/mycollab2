package com.esofthead.mycollab.core.format;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 *
 */
public class JpDateFormat implements IDateFormat {

	@Override
	public String getShortDateFormat() {
		return "yy/MM/dd";
	}

	@Override
	public String getDateFormat() {
		return "yyyy/MM/dd";
	}

	@Override
	public String getDateTimeFormat() {
		return "yyyy/MM/dd  hh:mm a";
	}

	@Override
	public String getDayMonthFormat() {
		return "MM/dd";
	}

}
