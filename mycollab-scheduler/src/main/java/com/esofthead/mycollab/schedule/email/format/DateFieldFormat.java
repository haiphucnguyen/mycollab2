package com.esofthead.mycollab.schedule.email.format;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class DateFieldFormat extends FieldFormat<Date> {

	public DateFieldFormat(String displayName) {
		super(displayName);
	}

	@Override
	public String formatField(Date value, String timeZone) {
		if (value == null)
			return new Span().write();

		return new Span().appendText(
				DateTimeUtils.converToStringWithUserTimeZone(value, timeZone))
				.write();
	}
}
