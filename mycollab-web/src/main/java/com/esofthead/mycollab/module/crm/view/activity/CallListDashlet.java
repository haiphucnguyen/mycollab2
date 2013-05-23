/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class CallListDashlet extends Depot {

	private CallTableDisplay tableItem;

	public CallListDashlet() {
		super("My Calls", new VerticalLayout());

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new CallTableDisplay(
					new String[] { "isClosed", "subject", "status" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new CallTableDisplay(
					new String[] { "isClosed", "subject", "startdate", "status" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER) });
		}
		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleCall call = (SimpleCall) event.getData();
						if ("isClosed".equals(event.getFieldName())) {
							call.setIsclosed(true);
							final CallService callService = AppContext
									.getSpringBean(CallService.class);
							callService.updateWithSession(call,
									AppContext.getUsername());
							display();
						}
					}
				});
		bodyContent.addComponent(tableItem);
	}

	public void display() {
		final CallSearchCriteria criteria = new CallSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setAssignUsers(new SetSearchField<String>(
				new String[] { AppContext.getUsername() }));
		criteria.setIsClosed(BitSearchField.FALSE);
		tableItem.setSearchCriteria(criteria);
	}
}
