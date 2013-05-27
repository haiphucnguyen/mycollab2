/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class EventRelatedItemListComp extends
		RelatedListComp<SimpleEvent, EventSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private final boolean allowCreateNew;

	public EventRelatedItemListComp(final boolean allowCreateNew) {
		super("Activities");

		this.allowCreateNew = allowCreateNew;

		initUI();
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;
		contentContainer.setSpacing(true);

		if (allowCreateNew) {
			final HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			final Button newTaskBtn = new Button("New Task",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("task");
						}
					});
			newTaskBtn
					.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
			newTaskBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_TASK));
			newTaskBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(newTaskBtn);

			final Button newCallBtn = new Button("New Call",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newCallBtn
					.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
			newCallBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_CALL));
			newCallBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(newCallBtn);

			final Button newMeetingBtn = new Button("New Meeting",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newMeetingBtn.setIcon(new ThemeResource(
					"icons/16/addRecordGreen.png"));
			newMeetingBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_MEETING));
			newMeetingBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(newMeetingBtn);

			contentContainer.addComponent(buttonControls);
		}

		tableItem = new EventTableDisplay(new String[] { "status", "eventType",
				"subject", "startDate", "endDate" }, new String[] { "Status",
				"Type", "Subject", "Start Date", "End Date" });

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleEvent simpleEvent = (SimpleEvent) event
								.getData();
						if ("Task".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this,
											simpleEvent.getId()));
						} else if ("Meeting".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingRead(this,
											simpleEvent.getId()));
						} else if ("Call".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this,
											simpleEvent.getId()));
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
