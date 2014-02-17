/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Arrays;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleActivity;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.AbstractListItemComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlersContainer;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class ActivityListViewImpl extends
		AbstractListItemComp<ActivitySearchCriteria, SimpleActivity> implements
		ActivityListView {
	private static final long serialVersionUID = 1L;

	@Override
	protected void buildExtraControls() {
		// do nothing

	}

	@Override
	protected GenericSearchPanel<ActivitySearchCriteria> createSearchPanel() {
		return new ActivitySearchPanel();
	}

	@Override
	protected AbstractPagedBeanTable<ActivitySearchCriteria, SimpleActivity> createBeanTable() {
		ActivityTableDisplay table = new ActivityTableDisplay(
				new TableViewField("", "selected",
						UIConstants.TABLE_CONTROL_WIDTH),
				Arrays.asList(
						new TableViewField(LocalizationHelper
								.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
								"subject", UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField(
								LocalizationHelper
										.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
								"status", UIConstants.TABLE_S_LABEL_WIDTH),
						new TableViewField(LocalizationHelper
								.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
								"eventType", UIConstants.TABLE_S_LABEL_WIDTH),
						new TableViewField(
								LocalizationHelper
										.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
								"startDate", UIConstants.TABLE_DATE_TIME_WIDTH),
						new TableViewField(
								LocalizationHelper
										.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER),
								"endDate", UIConstants.TABLE_DATE_TIME_WIDTH)));

		table.addTableListener(new ApplicationEventListener<TableClickEvent>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return TableClickEvent.class;
			}

			@Override
			public void handle(final TableClickEvent event) {
				final SimpleActivity simpleEvent = (SimpleActivity) event
						.getData();
				if ("Task".equals(simpleEvent.getEventType())) {
					EventBus.getInstance().fireEvent(
							new ActivityEvent.TaskRead(this, simpleEvent
									.getId()));
				} else if ("Event".equals(simpleEvent.getEventType())) {
					EventBus.getInstance().fireEvent(
							new ActivityEvent.MeetingRead(this, simpleEvent
									.getId()));
				} else if ("Call".equals(simpleEvent.getEventType())) {
					EventBus.getInstance().fireEvent(
							new ActivityEvent.CallRead(this, simpleEvent
									.getId()));
				}
			}
		});

		return table;
	}

	@Override
	protected DefaultMassItemActionHandlersContainer createActionControls() {
		DefaultMassItemActionHandlersContainer container = new DefaultMassItemActionHandlersContainer();
		if (AppContext.canAccess(RolePermissionCollections.CRM_CALL)
				|| AppContext.canAccess(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canAccess(RolePermissionCollections.CRM_TASK)) {
			container.addActionItem(MassItemActionHandler.DELETE_ACTION,
					MyCollabResource.newResource("icons/16/action/delete.png"),
					"delete");
		}

		container.addActionItem(MassItemActionHandler.MAIL_ACTION,
				MyCollabResource.newResource("icons/16/action/mail.png"),
				"mail");
		container.addDownloadActionItem(
				MassItemActionHandler.EXPORT_PDF_ACTION,
				MyCollabResource.newResource("icons/16/action/pdf.png"),
				"export", "export.pdf");
		container.addDownloadActionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION,
				MyCollabResource.newResource("icons/16/action/excel.png"),
				"export", "export.xlsx");
		container.addDownloadActionItem(
				MassItemActionHandler.EXPORT_CSV_ACTION,
				MyCollabResource.newResource("icons/16/action/csv.png"),
				"export", "export.csv");

		return container;
	}
}
