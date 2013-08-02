package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.localization.ProblemI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.LocalizationHelper;

public interface ProblemTableFieldDef {

	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField name = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_NAME),
			"issuename", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField description = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_DESCRIPTION),
			"description", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField raisedby = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_RAISED_BY),
			"raisedByUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_ASSIGN_USER),
			"assignedUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField datedue = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_DATE_DUE),
			"datedue", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField status = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_STATUS),
			"status", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField impact = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_IMPACT),
			"impact", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField priority = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_PRIORITY),
			"priority", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField rating = new TableViewField(
			LocalizationHelper.getMessage(ProblemI18nEnum.FORM_RATING),
			"level", UIConstants.TABLE_M_LABEL_WIDTH);
}
