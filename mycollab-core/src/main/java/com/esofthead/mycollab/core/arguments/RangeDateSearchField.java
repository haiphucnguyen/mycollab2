package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class RangeDateSearchField extends RangeDateTimeSearchField {

	public RangeDateSearchField() {
		this(null, null);
	}

	public RangeDateSearchField(Date from, Date to) {
		super(DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(from.getTime())),
				DateTimeUtils.convertDate(DateTimeUtils
						.convertTimeFromSystemTimezoneToUTC(to.getTime())));
	}

	public RangeDateSearchField(String oper, Date from, Date to) {
		super(oper, DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(from.getTime())),
				DateTimeUtils.convertDate(DateTimeUtils
						.convertTimeFromSystemTimezoneToUTC(to.getTime())));
	}
}
