/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Arrays;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
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
			newTaskBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newTaskBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_TASK));
			newTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			buttonControls.addComponent(newTaskBtn);

			final Button newCallBtn = new Button("New Call",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newCallBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newCallBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_CALL));
			newCallBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			buttonControls.addComponent(newCallBtn);

			final Button newMeetingBtn = new Button("New Meeting",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newMeetingBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newMeetingBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_MEETING));
			newMeetingBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			buttonControls.addComponent(newMeetingBtn);

			addHeaderElement(buttonControls);
		}

		tableItem = new EventTableDisplay(Arrays.asList(
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
						"subject", UIConstants.TABLE_EX_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						"status", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
						"eventType", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
						"startDate", UIConstants.TABLE_DATE_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER),
						"endDate", UIConstants.TABLE_DATE_WIDTH)));

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

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
		contentContainer.setSpacing(false);
	}

	@Override
	public void refresh() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
