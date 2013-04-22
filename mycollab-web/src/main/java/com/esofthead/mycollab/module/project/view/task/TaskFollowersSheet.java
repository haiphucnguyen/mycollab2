package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskNotificationService;
import com.esofthead.mycollab.module.project.ui.components.CompFollowersSheet;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class TaskFollowersSheet extends CompFollowersSheet {

	private SimpleTask task;

	protected TaskFollowersSheet(SimpleTask task) {
		super(task);
		this.task = task;
	}

	@Override
	protected void loadMonitorItems() {
		if (this.task == null) {
			this.task = (SimpleTask) bean;
		}

		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setTypeId(task.getId());
		searchCriteria.setType(MonitorTypeConstants.PRJ_TASK);
		tableItem.setSearchCriteria(searchCriteria);
	}

	@Override
	protected boolean saveMonitorItem(String username) {
		if (!monitorItemService.isUserWatchingItem(username,
				MonitorTypeConstants.PRJ_TASK, task.getId())) {

			MonitorItem monitorItem = new MonitorItem();
			monitorItem.setMonitorDate(new GregorianCalendar().getTime());
			monitorItem.setType(MonitorTypeConstants.PRJ_TASK);
			monitorItem.setTypeid(task.getId());
			monitorItem.setUser(username);
			monitorItemService.saveWithSession(monitorItem,
					AppContext.getUsername());
			return true;

		}
		return false;
	}

	@Override
	protected void saveRelayNotification() {
		RelayEmailNotification relayNotification = new RelayEmailNotification();
		relayNotification.setChangeby(AppContext.getUsername());
		relayNotification.setChangecomment("");
		relayNotification.setSaccountid(AppContext.getAccountId());
		relayNotification.setType(MonitorTypeConstants.PRJ_TASK);
		relayNotification.setTypeid(task.getId());
		relayNotification
				.setEmailhandlerbean(ProjectTaskNotificationService.class
						.getName());
		relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);

		RelayEmailNotificationService relayEmailNotificationService = AppContext
				.getSpringBean(RelayEmailNotificationService.class);
		relayEmailNotificationService.saveWithSession(relayNotification,
				AppContext.getUsername());
	}

}
