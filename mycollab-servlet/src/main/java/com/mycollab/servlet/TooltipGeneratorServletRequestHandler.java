package com.mycollab.servlet;

import com.mycollab.core.utils.TimezoneVal;
import com.mycollab.i18n.LocalizationHelper;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.page.domain.Page;
import com.mycollab.module.project.ProjectTooltipGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.service.*;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.domain.SimpleComponent;
import com.mycollab.module.tracker.domain.SimpleVersion;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.module.tracker.service.ComponentService;
import com.mycollab.module.tracker.service.VersionService;
import com.mycollab.module.user.AdminTypeConstants;
import com.mycollab.module.user.CommonTooltipGenerator;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.service.UserService;
import com.mycollab.module.crm.domain.*;
import com.mycollab.module.crm.service.*;
import com.mycollab.spring.AppContextUtil;
import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(urlPatterns = "/tooltip/*", name = "tooltipGeneratorServlet")
public class TooltipGeneratorServletRequestHandler extends GenericHttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TooltipGeneratorServletRequestHandler.class);

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            String typeId = request.getParameter("typeId");
            Integer sAccountId = Integer.parseInt(request.getParameter("sAccountId"));
            String siteURL = request.getParameter("siteURL");
            String timeZoneId = request.getParameter("timeZone");
            TimeZone timeZone = TimezoneVal.valueOf(timeZoneId);
            String username = request.getParameter("username");
            String localeParam = request.getParameter("locale");
            Locale locale = LocalizationHelper.getLocaleInstance(localeParam);
            String dateFormat = MoreObjects.firstNonNull(request.getParameter("dateFormat"), "MM/dd/yyyy");

            String html = "";
            if (ProjectTypeConstants.INSTANCE.getPROJECT().equals(type)) {
                ProjectService service = AppContextUtil.getSpringBean(ProjectService.class);
                SimpleProject project = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipProject(locale, dateFormat, project, siteURL, timeZone);
            } else if (ProjectTypeConstants.INSTANCE.getMESSAGE().equals(type)) {
                MessageService service = AppContextUtil.getSpringBean(MessageService.class);
                SimpleMessage message = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipMessage(locale, message, siteURL, timeZone);
            } else if (ProjectTypeConstants.INSTANCE.getMILESTONE().equals(type)) {
                MilestoneService service = AppContextUtil.getSpringBean(MilestoneService.class);
                SimpleMilestone mileStone = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipMilestone(locale, dateFormat, mileStone, siteURL, timeZone, false);
            } else if (ProjectTypeConstants.INSTANCE.getBUG().equals(type)) {
                BugService service = AppContextUtil.getSpringBean(BugService.class);
                SimpleBug bug = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipBug(locale, dateFormat, bug, siteURL, timeZone, false);
            } else if (ProjectTypeConstants.INSTANCE.getTASK().equals(type)) {
                ProjectTaskService service = AppContextUtil.getSpringBean(ProjectTaskService.class);
                SimpleTask task = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipTask(locale, dateFormat, task, siteURL, timeZone, false);
            } else if (ProjectTypeConstants.INSTANCE.getRISK().equals(type)) {
                RiskService service = AppContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipRisk(locale, dateFormat, risk, siteURL, timeZone, false);
            } else if (ProjectTypeConstants.INSTANCE.getBUG_VERSION().equals(type)) {
                VersionService service = AppContextUtil.getSpringBean(VersionService.class);
                SimpleVersion version = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipVersion(locale, dateFormat, version, siteURL, timeZone);
            } else if (ProjectTypeConstants.INSTANCE.getBUG_COMPONENT().equals(type)) {
                ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
                SimpleComponent component = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipComponent(locale, component, siteURL, timeZone);
            } else if (ProjectTypeConstants.INSTANCE.getPAGE().equals(type)) {
                ProjectPageService pageService = AppContextUtil.getSpringBean(ProjectPageService.class);
                Page page = pageService.getPage(typeId, username);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipPage(locale, page, siteURL, timeZone);
            } else if (ProjectTypeConstants.INSTANCE.getSTANDUP().equals(type)) {
                StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
                SimpleStandupReport standup = service.findById(Integer.parseInt(typeId), sAccountId);
                html = ProjectTooltipGenerator.INSTANCE.generateToolTipStandUp(locale, dateFormat, standup, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getACCOUNT().equals(type)) {
                AccountService service = AppContextUtil.getSpringBean(AccountService.class);
                SimpleAccount account = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateToolTipAccount(locale, account, siteURL);
            } else if (CrmTypeConstants.INSTANCE.getCONTACT().equals(type)) {
                ContactService service = AppContextUtil.getSpringBean(ContactService.class);
                SimpleContact contact = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateToolTipContact(locale, dateFormat, contact, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getCAMPAIGN().equals(type)) {
                CampaignService service = AppContextUtil.getSpringBean(CampaignService.class);
                SimpleCampaign account = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateTooltipCampaign(locale, dateFormat, account, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getLEAD().equals(type)) {
                LeadService service = AppContextUtil.getSpringBean(LeadService.class);
                SimpleLead lead = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateTooltipLead(locale, lead, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getOPPORTUNITY().equals(type)) {
                OpportunityService service = AppContextUtil.getSpringBean(OpportunityService.class);
                SimpleOpportunity opportunity = service.findById(
                        Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateTooltipOpportunity(locale, dateFormat, opportunity, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getCASE().equals(type)) {
                CaseService service = AppContextUtil.getSpringBean(CaseService.class);
                SimpleCase cases = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateTooltipCases(locale, cases, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getMEETING().equals(type)) {
                MeetingService service = AppContextUtil.getSpringBean(MeetingService.class);
                SimpleMeeting meeting = service.findById(
                        Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateToolTipMeeting(locale, dateFormat, meeting, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getCALL().equals(type)) {
                CallService service = AppContextUtil.getSpringBean(CallService.class);
                SimpleCall call = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateToolTipCall(locale, dateFormat, call, siteURL, timeZone);
            } else if (CrmTypeConstants.INSTANCE.getTASK().equals(type)) {
                TaskService service = AppContextUtil.getSpringBean(TaskService.class);
                SimpleCrmTask crmTask = service.findById(Integer.parseInt(typeId), sAccountId);
                html = CrmTooltipGenerator.INSTANCE.generateToolTipCrmTask(locale, dateFormat,
                        crmTask, siteURL, timeZone);
            } else if (AdminTypeConstants.USER.equals(type)) {
                UserService service = AppContextUtil.getSpringBean(UserService.class);
                SimpleUser user = service.findUserByUserNameInAccount(username, sAccountId);
                html = CommonTooltipGenerator.generateTooltipUser(locale, user, siteURL, timeZone);
            } else {
                LOG.error("Can not generate tooltip for item has type " + type);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(html);
        } catch (Exception e) {
            LOG.error("Error while get html tooltip attachForm TooltipGeneratorServletRequestHandler", e);
            String html = null;
            PrintWriter out = response.getWriter();
            out.println(html);
        }
    }
}
