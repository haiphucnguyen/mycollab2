/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class MeetingListDashlet extends Depot {

	private MeetingTableDisplay tableItem;

	public MeetingListDashlet() {
		super("My Meetings", new VerticalLayout());

		tableItem = new MeetingTableDisplay(Arrays.asList(
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
						"subject", UIConstants.TABLE_X_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
						"startdate", UIConstants.TABLE_DATE_TIME_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						"status", UIConstants.TABLE_S_LABEL_WIDTH)));

		bodyContent.addComponent(tableItem);
	}

	public void display() {
		final MeetingSearchCriteria criteria = new MeetingSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setAssignUsers(new SetSearchField<String>(
				new String[] { AppContext.getUsername() }));
		tableItem.setSearchCriteria(criteria);
	}
}
