package com.esofthead.mycollab.iexporter.csv;

public interface CSVFormatter<T> {
	T format(String value);
}
