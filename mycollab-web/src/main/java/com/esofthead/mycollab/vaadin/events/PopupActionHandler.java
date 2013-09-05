package com.esofthead.mycollab.vaadin.events;

public interface PopupActionHandler {
	public static final String MAIL_ACTION = "mail";

	public static final String EXPORT_CSV_ACTION = "exportCsv";

	public static final String EXPORT_PDF_ACTION = "exportPdf";

	public static final String EXPORT_EXCEL_ACTION = "exportExcel";

	public static final String DELETE_ACTION = "delete";

	public static final String MASS_UPDATE_ACTION = "massUpdate";

	void onSelect(String id, String caption);
}
