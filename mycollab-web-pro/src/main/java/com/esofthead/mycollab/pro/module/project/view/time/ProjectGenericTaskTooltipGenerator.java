package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
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

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
class ProjectGenericTaskTooltipGenerator {
    private String html;

    public ProjectGenericTaskTooltipGenerator(String type, int typeid) {
        html = "";
        int sAccountId = AppContext.getAccountId();
        TimeZone timeZone = AppContext.getUserTimezone();
        String siteURL = AppContext.getSiteUrl();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = ApplicationContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipBug(AppContext.getUserLocale(), bug, siteURL, timeZone);
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            ProjectTaskService service = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(), task, siteURL, timeZone);
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = ApplicationContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipRisk(AppContext.getUserLocale(), risk, siteURL, timeZone);
        } else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
            VersionService service = ApplicationContextUtil.getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipVersion(AppContext.getUserLocale(), version, siteURL, timeZone);
        } else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
            ComponentService service = ApplicationContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipComponent(AppContext.getUserLocale(), component, siteURL, timeZone);
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = ApplicationContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeid, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipStandUp(AppContext.getUserLocale(), standup, siteURL, timeZone);
        }
    }

    public String getContent() {
        return html;
    }
}