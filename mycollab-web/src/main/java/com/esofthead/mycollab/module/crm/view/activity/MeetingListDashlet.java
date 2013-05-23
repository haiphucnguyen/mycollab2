/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.Depot;
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

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new MeetingTableDisplay(
					new String[] { "subject", "status" },
					new String[] {
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new MeetingTableDisplay(
					new String[] { "subject", "startdate", "status" },
					new String[] {
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER) });
		}
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
