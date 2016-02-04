package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
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
import org.jsoup.Jsoup;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
public class GenericTaskDetailMapper {
    private String name;

    public GenericTaskDetailMapper(String type, int typeId) {
        int sAccountId = AppContext.getAccountId();
        TimeZone timeZone = AppContext.getUserTimezone();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = ApplicationContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            if (bug != null) {
                name = bug.getSummary();
            }
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            ProjectTaskService service = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            if (task != null) {
                name = task.getTaskname();
            }
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = ApplicationContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            if (risk != null) {
                name = risk.getRiskname();
            }
        } else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
            VersionService service = ApplicationContextUtil
                    .getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            if (version != null) {
                name = version.getVersionname();
            }
        } else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
            ComponentService service = ApplicationContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            if (component != null) {
                name = component.getComponentname();
            }
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = ApplicationContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            if (standup != null) {
                name = Jsoup.parse(DateTimeUtils.converToStringWithUserTimeZone(
                        standup.getCreatedtime(), AppContext.getUserDateFormat().getDateTimeFormat(), timeZone)).html();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}