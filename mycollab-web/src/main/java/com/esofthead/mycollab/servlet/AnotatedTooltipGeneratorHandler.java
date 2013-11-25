package com.esofthead.mycollab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ui.components.CrmTooltipGenerator;
import com.esofthead.mycollab.common.ui.components.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.service.TaskService;
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
			String timeZone = request.getParameter("timeZone");

			String html = "";
			if ("TaskList".equals(type)) {
				ProjectTaskListService service = ApplicationContextUtil
						.getSpringBean(ProjectTaskListService.class);
				SimpleTaskList taskList = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipTaskList(
						taskList, siteURL, timeZone);
			} else if ("Bug".equals(type)) {
				BugService service = ApplicationContextUtil
						.getSpringBean(BugService.class);
				SimpleBug bug = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipBug(bug, siteURL,
						timeZone);
			} else if ("Task".equals(type)) {
				ProjectTaskService service = ApplicationContextUtil
						.getSpringBean(ProjectTaskService.class);
				SimpleTask task = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipTask(task,
						siteURL, timeZone);
			} else if ("Risk".equals(type)) {
				RiskService service = ApplicationContextUtil
						.getSpringBean(RiskService.class);
				SimpleRisk risk = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipRisk(risk,
						siteURL, timeZone);
			} else if ("Problem".equals(type)) {
				ProblemService service = ApplicationContextUtil
						.getSpringBean(ProblemService.class);
				SimpleProblem problem = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipProblem(problem,
						siteURL, timeZone);
			} else if ("Version".equals(type)) {
				VersionService service = ApplicationContextUtil
						.getSpringBean(VersionService.class);
				SimpleVersion version = service.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipVersion(version,
						siteURL, timeZone);
			} else if ("Component".equals(type)) {
				ComponentService service = ApplicationContextUtil
						.getSpringBean(ComponentService.class);
				SimpleComponent component = service
						.findById(typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipComponent(
						component, siteURL, timeZone);
			} else if ("StandUp".equals(type)) {
				StandupReportService service = ApplicationContextUtil
						.getSpringBean(StandupReportService.class);
				SimpleStandupReport standup = service.findStandupReportById(
						typeid, sAccountId);
				html = ProjectTooltipGenerator.generateToolTipStandUp(standup,
						siteURL, timeZone);
			} else if ("Account".equals(type)) {
				AccountService service = ApplicationContextUtil
						.getSpringBean(AccountService.class);
				SimpleAccount account = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateToolTipAccount(account,
						siteURL, timeZone);
			} else if ("Contact".equals(type)) {
				ContactService service = ApplicationContextUtil
						.getSpringBean(ContactService.class);
				SimpleContact contact = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateToolTipContact(contact,
						siteURL, timeZone);
			} else if ("Campaign".equals(type)) {
				CampaignService service = ApplicationContextUtil
						.getSpringBean(CampaignService.class);
				SimpleCampaign account = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateTooltipCampaign(account,
						siteURL, timeZone);
			} else if ("Lead".equals(type)) {
				LeadService service = ApplicationContextUtil
						.getSpringBean(LeadService.class);
				SimpleLead lead = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateTooltipLead(lead, siteURL,
						timeZone);
			} else if ("Opportunity".equals(type)) {
				OpportunityService service = ApplicationContextUtil
						.getSpringBean(OpportunityService.class);
				SimpleOpportunity opportunity = service.findById(typeid,
						sAccountId);
				html = CrmTooltipGenerator.generateTooltipOpportunity(
						opportunity, siteURL, timeZone);
			} else if ("Case".equals(type)) {
				CaseService service = ApplicationContextUtil
						.getSpringBean(CaseService.class);
				SimpleCase cases = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateTooltipCases(cases, siteURL,
						timeZone);
			} else if ("Meeting".equals(type)) {
				MeetingService service = ApplicationContextUtil
						.getSpringBean(MeetingService.class);
				SimpleMeeting meeting = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateToolTipMeeting(meeting,
						siteURL, timeZone);
			} else if ("Call".equals(type)) {
				CallService service = ApplicationContextUtil
						.getSpringBean(CallService.class);
				SimpleCall account = service.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateToolTipCall(account,
						siteURL, timeZone);
			} else if ("CRMTask".equals(type)) {
				TaskService service = ApplicationContextUtil
						.getSpringBean(TaskService.class);
				com.esofthead.mycollab.module.crm.domain.SimpleTask crmTask = service
						.findById(typeid, sAccountId);
				html = CrmTooltipGenerator.generateToolTipCrmTask(crmTask,
						siteURL, timeZone);
			}
			PrintWriter out = response.getWriter();
			out.println(html);
			return;
		} catch (Exception e) {
			log.error(
					"Error while get html tooltip form AnotatedTooltipGeneratorHandler",
					e);
			String html = null;
			PrintWriter out = response.getWriter();
			out.println(html);
			return;
		}
	}
}
