package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public class FollowingTicketFieldDef {
	public static TableViewField summary = new TableViewField("Summary",
			"summary", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField project = new TableViewField("Project",
			"projectName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignee = new TableViewField(
			LocalizationHelper.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
			"assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField createdDate = new TableViewField(
			"Created Date", "monitorDate", UIConstants.TABLE_DATE_WIDTH);
}
