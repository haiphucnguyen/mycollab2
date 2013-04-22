package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.ui.components.CompFollowersSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugNotificationService;
import com.esofthead.mycollab.web.AppContext;

class BugFollowersSheet extends CompFollowersSheet {
	private static final long serialVersionUID = 1L;

	private SimpleBug bug;

	public BugFollowersSheet(SimpleBug bug) {
		super(bug);
		this.bug = bug;
	}

	@Override
	protected void loadMonitorItems() {
		if (this.bug == null) {
			this.bug = (SimpleBug) bean;
		}
		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setTypeId(bug.getId());
		searchCriteria.setType(MonitorTypeConstants.PRJ_BUG);
		tableItem.setSearchCriteria(searchCriteria);
	}

	@Override
	protected boolean saveMonitorItem(String username) {
		
		if (!monitorItemService.isUserWatchingItem(username,
				MonitorTypeConstants.PRJ_BUG, bug.getId())) {

			MonitorItem monitorItem = new MonitorItem();
			monitorItem.setMonitorDate(new GregorianCalendar()
					.getTime());
			monitorItem.setType(MonitorTypeConstants.PRJ_BUG);
			monitorItem.setTypeid(bug.getId());
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
		relayNotification.setSaccountid(AppContext
				.getAccountId());
		relayNotification.setType(MonitorTypeConstants.PRJ_BUG);
		relayNotification.setTypeid(bug.getId());
		relayNotification
				.setEmailhandlerbean(BugNotificationService.class
						.getName());
		relayNotification
				.setAction(MonitorTypeConstants.CREATE_ACTION);

		RelayEmailNotificationService relayEmailNotificationService = AppContext
				.getSpringBean(RelayEmailNotificationService.class);
		relayEmailNotificationService.saveWithSession(
				relayNotification, AppContext.getUsername());
	}
	
	

}
