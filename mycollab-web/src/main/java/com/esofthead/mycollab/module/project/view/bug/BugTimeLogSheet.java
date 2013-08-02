package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.CompTimeLogSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class BugTimeLogSheet extends CompTimeLogSheet<SimpleBug> {

	protected BugTimeLogSheet(SimpleBug bean) {
		super(bean);
	}

	@Override
	protected void saveTimeInvest() {
		ItemTimeLogging item = new ItemTimeLogging();
		item.setLoguser(AppContext.getUsername());
		item.setLogvalue(getInvestValue());
		item.setTypeid(bean.getId());
		item.setType(MonitorTypeConstants.PRJ_BUG);
		item.setProjectid(CurrentProjectVariables.getProjectId());

		itemTimeLoggingService.saveWithSession(item, AppContext.getUsername());

	}

	@Override
	protected void updateTimeRemain() {
		BugService bugService = AppContext.getSpringBean(BugService.class);
		bean.setEstimateremaintime(getUpdateRemainTime());
		bugService.updateWithSession(bean, AppContext.getUsername());
	}

	@Override
	protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
		ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setType(new StringSearchField(
				MonitorTypeConstants.PRJ_BUG));
		searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
		return searchCriteria;
	}

	@Override
	protected double getEstimateRemainTime() {
		if (bean.getEstimateremaintime() != null) {
			return bean.getEstimateremaintime();
		}
		return -1;
	}

	@Override
	protected boolean isEnableAdd() {
		return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
	}

}
