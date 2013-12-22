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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.DefaultMassEditActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class ActivityListPresenter
		extends
		ListSelectionPresenter<ActivityListView, EventSearchCriteria, SimpleEvent> {
	private static final long serialVersionUID = 1L;

	public ActivityListPresenter() {
		super(ActivityListView.class);
	}

	@Override
	protected void postInitView() {
		view.getPopupActionHandlers().addMassItemActionHandler(
				new DefaultMassEditActionHandler(this) {

					@Override
					protected void onSelectExtra(String id) {
						if (MassItemActionHandler.MAIL_ACTION.equals(id)) {
							UI.getCurrent().addWindow(new MailFormWindow());
						}
					}

					@Override
					protected String getReportTitle() {
						return "Event List";
					}

					@Override
					protected Class getReportModelClassType() {
						return SimpleEvent.class;
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canRead(RolePermissionCollections.CRM_TASK)
				|| AppContext.canRead(RolePermissionCollections.CRM_CALL)) {

			container.removeAllComponents();
			container.addComponent(view.getWidget());

			doSearch((EventSearchCriteria) data.getParams());
			AppContext.addFragment("crm/activity/todo", "Activity To Do");
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

	private static final String CALL = "Call";
	private static final String MEETING = "Meeting";
	private static final String TASK = "Task";

	@Override
	protected void deleteSelectedItems() {
		Collection<SimpleEvent> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		List<Integer> keyListCall = new ArrayList<Integer>();
		List<Integer> keyListMeeting = new ArrayList<Integer>();
		List<Integer> keyListTask = new ArrayList<Integer>();
		if (!isSelectAll) {
			for (SimpleEvent item : currentDataList) {
				if (item.isSelected()) {
					if (item.getEventType().equals(CALL)) {
						keyListCall.add(item.getId());
					} else if (item.getEventType().equals(MEETING)) {
						keyListMeeting.add(item.getId());
					} else if (item.getEventType().equals(TASK)) {
						keyListTask.add(item.getId());
					}
				}
			}
		} else {
			for (SimpleEvent item : currentDataList) {
				if (item.getEventType().equals(CALL)) {
					keyListCall.add(item.getId());
				} else if (item.getEventType().equals(MEETING)) {
					keyListMeeting.add(item.getId());
				} else if (item.getEventType().equals(TASK)) {
					keyListTask.add(item.getId());
				}
			}
		}

		if (keyListCall.size() > 0) {
			CallService callService = ApplicationContextUtil
					.getSpringBean(CallService.class);
			callService.massRemoveWithSession(keyListCall,
					AppContext.getUsername(), AppContext.getAccountId());
		}

		if (keyListMeeting.size() > 0) {
			MeetingService meetingService = ApplicationContextUtil
					.getSpringBean(MeetingService.class);
			meetingService.massRemoveWithSession(keyListMeeting,
					AppContext.getUsername(), AppContext.getAccountId());
		}

		if (keyListTask.size() > 0) {
			TaskService taskService = ApplicationContextUtil
					.getSpringBean(TaskService.class);
			taskService.massRemoveWithSession(keyListTask,
					AppContext.getUsername(), AppContext.getAccountId());
		}
		doSearch(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	@Override
	public ISearchableService<EventSearchCriteria> getSearchService() {
		return ApplicationContextUtil.getSpringBean(EventService.class);
	}

}
