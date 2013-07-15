package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ui.components.CompFollowersSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.web.AppContext;

class BugFollowersSheet extends CompFollowersSheet<SimpleBug> {
	private static final long serialVersionUID = 1L;

	public BugFollowersSheet(SimpleBug bug) {
		super(bug);
	}

	@Override
	protected void loadMonitorItems() {
		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
		searchCriteria.setType(new StringSearchField(MonitorTypeConstants.PRJ_BUG));
		tableItem.setSearchCriteria(searchCriteria);
	}

	@Override
	protected boolean saveMonitorItem(String username) {
		
		if (!monitorItemService.isUserWatchingItem(username,
				MonitorTypeConstants.PRJ_BUG, bean.getId())) {

			MonitorItem monitorItem = new MonitorItem();
			monitorItem.setMonitorDate(new GregorianCalendar()
					.getTime());
			monitorItem.setType(MonitorTypeConstants.PRJ_BUG);
			monitorItem.setTypeid(bean.getId());
			monitorItem.setUser(username);
			monitorItemService.saveWithSession(monitorItem,
					AppContext.getUsername());
			return true;
			
		}
		return false;
	}

	@Override
	protected void saveRelayNotification() {
		
	}

	@Override
	protected boolean isEnableAdd() {
		return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
	}
}