package com.esofthead.mycollab.iexporter.csv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;

public class CSVDateFormatter implements CSVFormatter<Date> {

	@Override
	public Date format(String value) {
		if (value.length() == 0 || value.trim().length() == 0)
			return null;
		else {
			try {
				DateFormat formatter = new SimpleDateFormat(
						AppContext.getDateFormat());
				formatter.setLenient(false);
				return formatter.parse(value);
			} catch (Exception e) {
				throw new MyCollabException("Can't parse value = \'" + value
						+ "\' to DateType, please input follow mm/dd/yyyy.");
			}
		}
	}

}
