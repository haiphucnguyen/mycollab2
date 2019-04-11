package com.mycollab.pro.module.project.view.finance;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleStandupReport;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.service.TaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.service.StandupReportService;
import com.mycollab.module.project.domain.SimpleBug;
import com.mycollab.module.project.domain.SimpleComponent;
import com.mycollab.module.project.domain.SimpleVersion;
import com.mycollab.module.project.service.BugService;
import com.mycollab.module.project.service.ComponentService;
import com.mycollab.module.project.service.VersionService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import org.jsoup.Jsoup;

import java.time.ZoneId;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
public class GenericTicketDetailMapper {

    public static ProjectTicket getTicket(String type, int typeId) {
        ProjectTicket ticket = new ProjectTicket();
        ticket.setType(type);
        ticket.setTypeId(typeId);
        int sAccountId = AppUI.getAccountId();
        ZoneId timeZone = UserUIContext.getUserTimeZone();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            if (bug != null) {
                ticket.setName(bug.getName());
                ticket.setProjectId(bug.getProjectid());
                ticket.setProjectShortName(bug.getProjectShortName());
            } else return null;
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            TaskService service = AppContextUtil.getSpringBean(TaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            if (task != null) {
                ticket.setName(task.getName());
                ticket.setProjectId(task.getProjectid());
                ticket.setProjectShortName(task.getProjectShortname());
            } else return null;
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            if (risk != null) {
                ticket.setName(risk.getName());
                ticket.setProjectId(risk.getProjectid());
                ticket.setProjectShortName(risk.getProjectShortName());
            } else return null;
        } else if (ProjectTypeConstants.VERSION.equals(type)) {
            VersionService service = AppContextUtil
                    .getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            if (version != null) {
                ticket.setName(version.getName());
                ticket.setProjectId(version.getProjectid());
                ticket.setProjectShortName(version.getProjectShortName());
            } else return null;
        } else if (ProjectTypeConstants.COMPONENT.equals(type)) {
            ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            if (component != null) {
                ticket.setName(component.getName());
                ticket.setProjectId(component.getProjectid());
                ticket.setName(component.getProjectShortName());
            } else return null;
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            if (standup != null) {
                ticket.setName(Jsoup.parse(DateTimeUtils.convertDateTimeToStringWithUserTimeZone(
                        standup.getCreatedtime(), AppUI.getDateFormat(), UserUIContext.getUserLocale(), timeZone)).html());
                ticket.setProjectId(standup.getProjectid());
                ticket.setProjectShortName(standup.getProjectShortName());
            } else return null;
        } else {
            throw new MyCollabException("Not support type " + type);
        }
        return ticket;
    }

}
