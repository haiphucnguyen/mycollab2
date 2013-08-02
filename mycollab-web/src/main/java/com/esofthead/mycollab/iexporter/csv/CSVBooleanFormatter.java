package com.esofthead.mycollab.iexporter.csv;

public class CSVBooleanFormatter implements CSVFormatter<Boolean> {

	@Override
	public Boolean format(String value) {
		return (value.equals("true")) ? true : false;
	}

}
