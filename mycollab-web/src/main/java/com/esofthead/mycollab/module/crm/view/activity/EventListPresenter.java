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
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class EventListPresenter extends
		ListSelectionPresenter<EventListView, EventSearchCriteria, SimpleEvent> {
	private static final long serialVersionUID = 1L;

	public EventListPresenter() {
		super(EventListView.class);

		view.getPopupActionHandlers().addPopupActionHandler(
				new DefaultPopupActionHandler(this) {

					@Override
					protected void onSelectExtra(String id, String caption) {
						if (PopupActionHandler.MAIL_ACTION.equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
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
			MessageConstants.showMessagePermissionAlert();
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
			CallService callService = AppContext
					.getSpringBean(CallService.class);
			callService.massRemoveWithSession(keyListCall,
					AppContext.getUsername(), AppContext.getAccountId());
		}

		if (keyListMeeting.size() > 0) {
			MeetingService meetingService = AppContext
					.getSpringBean(MeetingService.class);
			meetingService.massRemoveWithSession(keyListMeeting,
					AppContext.getUsername(), AppContext.getAccountId());
		}

		if (keyListTask.size() > 0) {
			TaskService taskService = AppContext
					.getSpringBean(TaskService.class);
			taskService.massRemoveWithSession(keyListTask,
					AppContext.getUsername(), AppContext.getAccountId());
		}
		doSearch(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	@Override
	public ISearchableService<EventSearchCriteria> getSearchService() {
		return AppContext.getSpringBean(EventService.class);
	}

}
