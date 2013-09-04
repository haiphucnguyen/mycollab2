package com.esofthead.mycollab.vaadin.events;

public interface ExportOptionHandler {
	public static int CSV_OUTPUT = 1;

	public static int PDF_OUTPUT = 2;

	public static int EXCEL_OUTPUT = 3;
	
	void doExport();
}
