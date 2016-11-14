package com.mycollab.pro.module.project.view.reports;

import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.mycollab.module.project.view.reports.IReportContainer;
import com.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.hp.gagawa.java.elements.A;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class ReportContainerImpl extends AbstractVerticalPageView implements IReportContainer {
    private MVerticalLayout body;

    public ReportContainerImpl() {
        this.addStyleName("hdr-view");
        ReportBreadcrumb breadcrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
        body = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false));
        with(breadcrumb, ELabel.hr(), body);
    }

    @Override
    public void showDashboard() {
        body.removeAllComponents();
        body.with(ELabel.h2(FontAwesome.PIE_CHART.getHtml() + " " + UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS)));
        CssLayout content = new CssLayout();
        content.setStyleName(WebThemes.FLEX_DISPLAY);

        MVerticalLayout standupConsole = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        standupConsole.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        standupConsole.addComponent(ELabel.fontIcon(FontAwesome.LEGAL).withStyleName("icon-38px"));
        A standupReportLink = new A(ProjectLinkBuilder.generateStandupDashboardLink())
                .appendText(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_STANDUP));
        standupConsole.addComponent(ELabel.h3(standupReportLink.write()).withWidthUndefined());
        standupConsole.addComponent(new ELabel(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_STANDUP_HELP)).withFullWidth());
        content.addComponent(standupConsole);

        MVerticalLayout hoursWeeklyReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        hoursWeeklyReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        hoursWeeklyReport.addComponent(ELabel.fontIcon(FontAwesome.BALANCE_SCALE).withStyleName("icon-38px"));
        A hoursWeeklyReportLink = new A(ProjectLinkBuilder.generateHoursWeeklyReportLink())
                .appendText(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY));
        hoursWeeklyReport.addComponent(ELabel.h3(hoursWeeklyReportLink.write()).withWidthUndefined());
        hoursWeeklyReport.addComponent(new ELabel(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY_HELP))
                .withFullWidth());
        content.addComponent(hoursWeeklyReport);

        MVerticalLayout userWorkloadReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        userWorkloadReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        userWorkloadReport.addComponent(ELabel.fontIcon(FontAwesome.BAR_CHART).withStyleName("icon-38px"));
        A userWorkloadReportLink = new A(ProjectLinkBuilder.generateUsersWorkloadReportLink())
                .appendText(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD));
        userWorkloadReport.addComponent(ELabel.h3(userWorkloadReportLink.write()).withWidthUndefined());
        userWorkloadReport.addComponent(new ELabel(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD_HELP)).withFullWidth());
//        content.addComponent(userWorkloadReport);

        MVerticalLayout timesheetReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        timesheetReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        timesheetReport.addComponent(ELabel.fontIcon(FontAwesome.CLOCK_O).withStyleName("icon-38px"));
        A timesheetReportLink = new A(ProjectLinkBuilder.generateTimesheetReportLink())
                .appendText(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_TIMESHEET));
        timesheetReport.addComponent(ELabel.h3(timesheetReportLink.write()).withWidthUndefined());
        timesheetReport.addComponent(new ELabel(UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_TIMESHEET_HELP))
                .withFullWidth());
        content.addComponent(timesheetReport);

        body.with(content);
    }

    @Override
    public void addView(PageView view) {
        body.removeAllComponents();
        body.with(view).expand(view);
    }
}
