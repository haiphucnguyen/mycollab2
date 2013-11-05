package com.esofthead.mycollab.core.arguments;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import java.util.Date;

public class DateSearchField extends DateTimeSearchField {

	public DateSearchField() {
		this(AND, null, null);
	}

	public DateSearchField(String oper, Date value) {
		super(oper, DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(value.getTime())));
	}

	public DateSearchField(String oper, String comparision, Date value) {
		super(oper, comparision, DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(value.getTime())));
	}
}
