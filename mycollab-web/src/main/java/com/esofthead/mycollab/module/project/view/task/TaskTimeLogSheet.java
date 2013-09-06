package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.CompTimeLogSheet;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class TaskTimeLogSheet extends CompTimeLogSheet<SimpleTask> {

	protected TaskTimeLogSheet(SimpleTask bean) {
		super(bean);
	}

	@Override
	protected void saveTimeInvest() {
		ItemTimeLogging item = new ItemTimeLogging();
		item.setLoguser(AppContext.getUsername());
		item.setLogvalue(getInvestValue());
		item.setTypeid(bean.getId());
		item.setType(MonitorTypeConstants.PRJ_TASK);
		item.setSaccountid(AppContext.getAccountId());
		item.setProjectid(CurrentProjectVariables.getProjectId());

		itemTimeLoggingService.saveWithSession(item, AppContext.getUsername());
	}

	@Override
	protected void updateTimeRemain() {
		ProjectTaskService bugService = AppContext
				.getSpringBean(ProjectTaskService.class);
		bean.setRemainestimate(getUpdateRemainTime());
		bugService.updateWithSession(bean, AppContext.getUsername());
	}

	@Override
	protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
		ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		searchCriteria.setType(new StringSearchField(
				MonitorTypeConstants.PRJ_TASK));
		searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
		return searchCriteria;
	}

	@Override
	protected double getEstimateRemainTime() {
		if (bean.getRemainestimate() != null) {
			return bean.getRemainestimate();
		}
		return -1;
	}

	@Override
	protected boolean isEnableAdd() {
		return CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.TASKS);
	}

}
