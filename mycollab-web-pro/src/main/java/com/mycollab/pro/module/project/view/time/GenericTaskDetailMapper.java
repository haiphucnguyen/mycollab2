package com.mycollab.pro.module.project.view.time;

import com.mycollab.core.utils.DateTimeUtils;
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
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import org.jsoup.Jsoup;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd.
 * @since 4.0.0
 */
public class GenericTaskDetailMapper {
    private String name;

    public GenericTaskDetailMapper(String type, int typeId) {
        int sAccountId = MyCollabUI.getAccountId();
        TimeZone timeZone = UserUIContext.getUserTimeZone();

        if (ProjectTypeConstants.BUG.equals(type)) {
            BugService service = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = service.findById(typeId, sAccountId);
            if (bug != null) {
                name = bug.getName();
            }
        } else if (ProjectTypeConstants.TASK.equals(type)) {
            ProjectTaskService service = AppContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = service.findById(typeId, sAccountId);
            if (task != null) {
                name = task.getName();
            }
        } else if (ProjectTypeConstants.RISK.equals(type)) {
            RiskService service = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = service.findById(typeId, sAccountId);
            if (risk != null) {
                name = risk.getName();
            }
        } else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
            VersionService service = AppContextUtil
                    .getSpringBean(VersionService.class);
            SimpleVersion version = service.findById(typeId, sAccountId);
            if (version != null) {
                name = version.getVersionname();
            }
        } else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
            ComponentService service = AppContextUtil.getSpringBean(ComponentService.class);
            SimpleComponent component = service.findById(typeId, sAccountId);
            if (component != null) {
                name = component.getComponentname();
            }
        } else if (ProjectTypeConstants.STANDUP.equals(type)) {
            StandupReportService service = AppContextUtil.getSpringBean(StandupReportService.class);
            SimpleStandupReport standup = service.findById(typeId, sAccountId);
            if (standup != null) {
                name = Jsoup.parse(DateTimeUtils.convertToStringWithUserTimeZone(
                        standup.getCreatedtime(), MyCollabUI.getDateFormat(), UserUIContext.getUserLocale(), timeZone)).html();
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
