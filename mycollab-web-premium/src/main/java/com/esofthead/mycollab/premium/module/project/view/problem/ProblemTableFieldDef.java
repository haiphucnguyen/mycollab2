package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.module.project.localization.ProblemI18nEnum;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProblemTableFieldDef {

	public static TableViewField selected = new TableViewField("", "selected",
			UIConstants.TABLE_CONTROL_WIDTH);

	public static TableViewField action = new TableViewField("", "id");

	public static TableViewField name = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_NAME),
			"issuename", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField description = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_DESCRIPTION),
			"description", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField raisedby = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_RAISED_BY),
			"raisedByUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField assignUser = new TableViewField(
			AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
			"assignedUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField datedue = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE),
			"datedue", UIConstants.TABLE_DATE_WIDTH);

	public static TableViewField status = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_STATUS),
			"status", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField impact = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_IMPACT),
			"impact", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField priority = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY),
			"priority", UIConstants.TABLE_S_LABEL_WIDTH);

	public static TableViewField rating = new TableViewField(
			AppContext.getMessage(ProblemI18nEnum.FORM_RATING),
			"level", UIConstants.TABLE_M_LABEL_WIDTH);
}
