package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.ui.components.ProjectTooltipGenerator;
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

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0.0
 * 
 */
public class ProjectGenericTaskTooltipGenerator {

	private String html;

	public ProjectGenericTaskTooltipGenerator(String type, int typeid) {

		html = "";
		int sAccountId = AppContext.getAccountId();
		String timeZone = AppContext.getSession().getTimezone();
		String siteURL = AppContext.getSiteUrl();

		if (ProjectTypeConstants.TASK_LIST.equals(type)) {
			ProjectTaskListService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskListService.class);

			SimpleTaskList taskList = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipTaskList(taskList,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG.equals(type)) {
			BugService service = ApplicationContextUtil
					.getSpringBean(BugService.class);
			SimpleBug bug = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipBug(bug, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.TASK.equals(type)) {
			ProjectTaskService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskService.class);
			SimpleTask task = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipTask(task, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.RISK.equals(type)) {
			RiskService service = ApplicationContextUtil
					.getSpringBean(RiskService.class);
			SimpleRisk risk = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipRisk(risk, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.PROBLEM.equals(type)) {
			ProblemService service = ApplicationContextUtil
					.getSpringBean(ProblemService.class);
			SimpleProblem problem = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipProblem(problem,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
			VersionService service = ApplicationContextUtil
					.getSpringBean(VersionService.class);
			SimpleVersion version = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipVersion(version,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
			ComponentService service = ApplicationContextUtil
					.getSpringBean(ComponentService.class);
			SimpleComponent component = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipComponent(component,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.STANDUP.equals(type)) {
			StandupReportService service = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport standup = service.findStandupReportById(typeid,
					sAccountId);
			html = ProjectTooltipGenerator.generateToolTipStandUp(standup,
					siteURL, timeZone);
		}
	}

	public String getContent() {
		return html;
	}
}
