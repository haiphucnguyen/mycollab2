package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class TimeTrackingFieldDef {
	public static TableViewField summary = new TableViewField("Summary",
			"summary", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField logUser = new TableViewField("User",
			"logUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField project = new TableViewField("Project",
			"projectName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField createdTime = new TableViewField(
			"Created Time", "createdtime", UIConstants.TABLE_DATE_TIME_WIDTH);

	public static TableViewField timeLogValue = new TableViewField("Hours",
			"logvalue", UIConstants.TABLE_S_LABEL_WIDTH);
}
