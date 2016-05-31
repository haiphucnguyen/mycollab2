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
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
class ProjectGenericTaskTooltipGenerator {
    private String html;

    public ProjectGenericTaskTooltipGenerator(String type, int typeId) {
        html = "";
        int sAccountId = AppContext.getAccountId();
        TimeZone timeZone = AppContext.getUserTimeZone();
        String siteURL = AppContext.getSiteUrl();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipBug(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                    bug, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            ProjectTaskService service = AppContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                    task, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipRisk(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                    risk, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
            VersionService service = AppContextUtil.getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipVersion(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                    version, siteURL, timeZone);
        } else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
            ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipComponent(AppContext.getUserLocale(), component, siteURL, timeZone);
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.generateToolTipStandUp(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                    standup, siteURL, timeZone);
        }
    }

    public String getContent() {
        return html;
    }
}
