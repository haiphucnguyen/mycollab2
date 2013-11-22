package com.esofthead.mycollab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ui.components.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProblemService;
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

@Component("tooltipGeneratorServlet")
public class AnotatedTooltipGeneratorHandler extends GenericServlet {
	private static Logger log = LoggerFactory
			.getLogger(AnotatedTooltipGeneratorHandler.class);

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			Integer typeid = Integer.parseInt(request.getParameter("typeId"));
			Integer sAccountId = Integer.parseInt(request
					.getParameter("sAccountId"));
			String siteURL = request.getParameter("siteURL");

			String html = "";
			if ("Bug".equals(type)) {
				BugService service = ApplicationContextUtil
						.getSpringBean(BugService.class);
				SimpleBug bug = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipBug(bug, siteURL);
			} else if ("Task".equals(type)) {
				ProjectTaskService service = ApplicationContextUtil
						.getSpringBean(ProjectTaskService.class);
				SimpleTask task = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipTask(task,
						siteURL);
			} else if ("Risk".equals(type)) {
				RiskService service = ApplicationContextUtil
						.getSpringBean(RiskService.class);
				SimpleRisk risk = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipRisk(risk,
						siteURL);
			} else if ("Problem".equals(type)) {
				ProblemService service = ApplicationContextUtil
						.getSpringBean(ProblemService.class);
				SimpleProblem problem = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipProblem(problem,
						siteURL);
			} else if ("Version".equals(type)) {
				VersionService service = ApplicationContextUtil
						.getSpringBean(VersionService.class);
				SimpleVersion version = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipVersion(version,
						siteURL);
			} else if ("Component".equals(type)) {
				ComponentService service = ApplicationContextUtil
						.getSpringBean(ComponentService.class);
				SimpleComponent component = service
						.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipComponent(
						component, siteURL);
			} else if ("StandUp".equals(type)) {
				StandupReportService service = ApplicationContextUtil
						.getSpringBean(StandupReportService.class);
				SimpleStandupReport standup = service.findStandupReportById(
						typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipStandUp(standup,
						siteURL);
			}
			PrintWriter out = response.getWriter();
			out.println(html);
			return;
		} catch (Exception e) {
			Log.error(
					"Error while get html tooltip form AnotatedTooltipGeneratorHandler",
					e);
			String html = null;
			PrintWriter out = response.getWriter();
			out.println(html);
			return;
		}
	}
}
