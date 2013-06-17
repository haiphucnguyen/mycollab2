/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.cases.CaseTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class AccountCaseListComp extends
		RelatedListComp<SimpleCase, CaseSearchCriteria> {

	private static final long serialVersionUID = 1L;

	public AccountCaseListComp() {
		super("Cases");

		initUI();
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;

		final Button newBtn = new Button("New Case",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						fireNewRelatedItem("");
					}
				});
		newBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecordGreen.png"));
		newBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		newBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CASE));

		addHeaderElement(newBtn);

		tableItem = new CaseTableDisplay(new String[] { "subject", "priority",
				"status", "assignUserFullName", "createdtime" },
				new String[] { "Subject", "Priority", "Status", "Assigned To",
						"Date Created" });

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleCase cases = (SimpleCase) event.getData();
						if ("subject".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new CaseEvent.GotoRead(this, cases
													.getId()));
						}
					}
				});

		contentContainer.addComponent(tableItem);
	}

	@Override
	public void refresh() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
