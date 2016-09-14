package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.ProjectTooltipGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleStandupReport;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.service.StandupReportService;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.domain.SimpleComponent;
import com.mycollab.module.tracker.domain.SimpleVersion;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.module.tracker.service.ComponentService;
import com.mycollab.module.tracker.service.VersionService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
class ProjectGenericTaskTooltipGenerator {
    private String html;

    ProjectGenericTaskTooltipGenerator(String type, int typeId) {
        html = "";
        int sAccountId = UserUIContext.getAccountId();
        TimeZone timeZone = UserUIContext.getUserTimeZone();
        String siteURL = UserUIContext.getSiteUrl();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipBug(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                    bug, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            ProjectTaskService service = AppContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipTask(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                    task, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipRisk(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                    risk, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
            VersionService service = AppContextUtil.getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipVersion(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                    version, siteURL, timeZone);
        } else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
            ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipComponent(UserUIContext.getUserLocale(), component, siteURL, timeZone);
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipStandUp(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                    standup, siteURL, timeZone);
        }
    }

    public String getContent() {
        return html;
    }
}
