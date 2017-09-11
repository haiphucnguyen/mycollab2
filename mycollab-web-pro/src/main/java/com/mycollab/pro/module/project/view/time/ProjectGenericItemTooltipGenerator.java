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
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
class ProjectGenericItemTooltipGenerator {
    private String html;

    ProjectGenericItemTooltipGenerator(String type, int typeId) {
        html = "";
        Integer sAccountId = AppUI.getAccountId();
        TimeZone timeZone = UserUIContext.getUserTimeZone();
        String siteURL = AppUI.getSiteUrl();

        if (ProjectTypeConstants.INSTANCE.getBUG().equals(type)) {
            BugService service = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipBug(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    bug, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.INSTANCE.getTASK().equals(type)) {
            ProjectTaskService service = AppContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipTask(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    task, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.INSTANCE.getRISK().equals(type)) {
            RiskService service = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipRisk(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    risk, siteURL, timeZone, false);
        } else if (ProjectTypeConstants.INSTANCE.getBUG_VERSION().equals(type)) {
            VersionService service = AppContextUtil.getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipVersion(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    version, siteURL, timeZone);
        } else if (ProjectTypeConstants.INSTANCE.getBUG_COMPONENT().equals(type)) {
            ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipComponent(UserUIContext.getUserLocale(), component, siteURL, timeZone);
        } else if (ProjectTypeConstants.INSTANCE.getSTANDUP().equals(type)) {
            StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            html = ProjectTooltipGenerator.INSTANCE.generateToolTipStandUp(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    standup, siteURL, timeZone);
        }
    }

    public String getContent() {
        return html;
    }
}
