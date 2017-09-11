package com.mycollab.pro.module.project.view;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.event.ReportEvent;
import com.mycollab.module.project.i18n.BreadcrumbI18nEnum;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.breadcrumb.Breadcrumb;
import com.vaadin.ui.Button;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class ReportBreadcrumb extends Breadcrumb implements CacheableComponent {
    private Button homeBtn;

    public ReportBreadcrumb() {
        this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setUseDefaultClickBehaviour(false);
        homeBtn = new Button(null, clickEvent -> EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this)));
        this.addLink(homeBtn);
    }

    public void gotoReportDashboard() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS)));
        AppUI.addFragment("project/reports", UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS));
    }

    public void gotoStandupList(Date onDate) {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDUP)));
        if (onDate == null) {
            AppUI.addFragment("project/reports/standup/list/",
                    UserUIContext.getMessage(BreadcrumbI18nEnum.FRA_STANDUP));
        } else {
            AppUI.addFragment("project/reports/standup/list/" + UserUIContext.formatDate(onDate).replace('/', '-'),
                    UserUIContext.getMessage(BreadcrumbI18nEnum.FRA_STANDUP));
        }
    }

    public void gotoTimesheetReport() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_TIMESHEET)));
    }

    public void gotoWeeklyTimingReport() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY)));
    }

    public void gotoUserWorkloadReport() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD)));
        AppUI.addFragment(ProjectLinkGenerator.INSTANCE.generateUsersWorkloadReportLink(), UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD));
    }

    private static class GotoReportsListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(Button.ClickEvent event) {
            EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this));
        }
    }
}
