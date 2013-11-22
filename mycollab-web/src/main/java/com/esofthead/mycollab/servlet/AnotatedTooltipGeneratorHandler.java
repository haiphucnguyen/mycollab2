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
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
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
				html = (bug != null) ? ProjectTooltipGenerator
						.generateToolTipBug(bug, siteURL) : "";
			} else if ("Task".equals(type)) {
				ProjectTaskService service = ApplicationContextUtil
						.getSpringBean(ProjectTaskService.class);
				SimpleTask task = service.findById(typeid, sAccountId);
				html = (task != null) ? ProjectTooltipGenerator
						.generateToolTipTask(task, siteURL) : "";
			} else if ("Risk".equals(type)) {
				html = null;
			} else if ("Problem".equals(type)) {
				html = null;
			} else if ("Version".equals(type)) {
				html = null;
			} else if ("Component".equals(type)) {
				html = null;
			} else if ("StandUp".equals(type)) {
				html = null;
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
