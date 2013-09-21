package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.ui.components.CompFollowersSheet;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class TaskFollowersSheet extends CompFollowersSheet<SimpleTask> {

	protected TaskFollowersSheet(SimpleTask task) {
		super(task);
	}

	@Override
	protected void loadMonitorItems() {
		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
		searchCriteria.setType(new StringSearchField(
				MonitorTypeConstants.PRJ_TASK));
		tableItem.setSearchCriteria(searchCriteria);
	}

	@Override
	protected boolean saveMonitorItem(String username) {
		if (!monitorItemService.isUserWatchingItem(username,
				MonitorTypeConstants.PRJ_TASK, bean.getId())) {

			MonitorItem monitorItem = new MonitorItem();
			monitorItem.setMonitorDate(new GregorianCalendar().getTime());
			monitorItem.setType(MonitorTypeConstants.PRJ_TASK);
			monitorItem.setTypeid(bean.getId());
			monitorItem.setUser(username);
			monitorItem.setSaccountid(AppContext.getAccountId());
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
		relayNotification.setTypeid(bean.getId());
		relayNotification
				.setEmailhandlerbean(ProjectTaskRelayEmailNotificationAction.class
						.getName());
		relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);

		RelayEmailNotificationService relayEmailNotificationService = ApplicationContextUtil
				.getSpringBean(RelayEmailNotificationService.class);
		relayEmailNotificationService.saveWithSession(relayNotification,
				AppContext.getUsername());
	}

	@Override
	protected boolean isEnableAdd() {
		return CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS);
	}

}