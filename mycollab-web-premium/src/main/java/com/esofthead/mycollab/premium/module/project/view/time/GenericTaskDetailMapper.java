package com.esofthead.mycollab.premium.module.project.view.time;

import org.jsoup.Jsoup;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

public class GenericTaskDetailMapper {

	private String name;

	public GenericTaskDetailMapper(String type, int typeid) {

		int sAccountId = AppContext.getAccountId();
		String timeZone = AppContext.getSession().getTimezone();

		if (ProjectTypeConstants.TASK_LIST.equals(type)) {
			ProjectTaskListService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskListService.class);

			SimpleTaskList taskList = service.findById(typeid, sAccountId);
			if (taskList != null) {
				name = taskList.getName();
			}
		} else if (ProjectTypeConstants.BUG.equals(type)) {
			BugService service = ApplicationContextUtil
					.getSpringBean(BugService.class);
			SimpleBug bug = service.findById(typeid, sAccountId);
			if (bug != null) {
				name = bug.getSummary();
			}
		} else if (ProjectTypeConstants.TASK.equals(type)) {
			ProjectTaskService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskService.class);
			SimpleTask task = service.findById(typeid, sAccountId);
			if (task != null) {
				name = task.getTaskname();
			}
		} else if (ProjectTypeConstants.RISK.equals(type)) {
			RiskService service = ApplicationContextUtil
					.getSpringBean(RiskService.class);
			SimpleRisk risk = service.findById(typeid, sAccountId);
			if (risk != null) {
				name = risk.getRiskname();
			}
		} else if (ProjectTypeConstants.PROBLEM.equals(type)) {
			ProblemService service = ApplicationContextUtil
					.getSpringBean(ProblemService.class);
			SimpleProblem problem = service.findById(typeid, sAccountId);
			if (problem != null) {
				name = problem.getIssuename();
			}
		} else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
			VersionService service = ApplicationContextUtil
					.getSpringBean(VersionService.class);
			SimpleVersion version = service.findById(typeid, sAccountId);
			if (version != null) {
				name = version.getVersionname();
			}
		} else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
			ComponentService service = ApplicationContextUtil
					.getSpringBean(ComponentService.class);
			SimpleComponent component = service.findById(typeid, sAccountId);
			if (component != null) {
				name = component.getComponentname();
			}
		} else if (ProjectTypeConstants.STANDUP.equals(type)) {
			StandupReportService service = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport standup = service.findStandupReportById(typeid,
					sAccountId);
			if (standup != null) {
				name = Jsoup.parse(
						DateTimeUtils.converToStringWithUserTimeZone(
								standup.getCreatedtime(), timeZone)).html();
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
