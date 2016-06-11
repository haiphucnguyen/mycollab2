package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
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
public class ReportContainerImpl extends AbstractPageView implements IReportContainer {
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
        body.with(ELabel.h2(FontAwesome.PIE_CHART.getHtml() + " " + AppContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS)));
        CssLayout content = new CssLayout();
        content.setStyleName(UIConstants.FLEX_DISPLAY);
        this.addComponent(content);

        MVerticalLayout standupConsole = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        standupConsole.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        standupConsole.addComponent(ELabel.fontIcon(FontAwesome.LEGAL).withStyleName("icon-38px"));
        A standupReportLink = new A(ProjectLinkBuilder.generateStandupDashboardLink())
                .appendText(AppContext.getMessage(ProjectReportI18nEnum.REPORT_STANDUP));
        standupConsole.addComponent(ELabel.h3(standupReportLink.write()).withWidthUndefined());
        standupConsole.addComponent(new ELabel(AppContext.getMessage(ProjectReportI18nEnum.REPORT_STANDUP_HELP)).withFullWidth());
        content.addComponent(standupConsole);

        MVerticalLayout hoursWeeklyReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        hoursWeeklyReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        hoursWeeklyReport.addComponent(ELabel.fontIcon(FontAwesome.BALANCE_SCALE).withStyleName("icon-38px"));
        A hoursWeeklyReportLink = new A(ProjectLinkBuilder.generateHoursWeeklyReportLink())
                .appendText(AppContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY));
        hoursWeeklyReport.addComponent(ELabel.h3(hoursWeeklyReportLink.write()).withWidthUndefined());
        hoursWeeklyReport.addComponent(new ELabel(AppContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY_HELP))
                .withFullWidth());
//        content.addComponent(hoursWeeklyReport);

        MVerticalLayout userWorkloadReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        userWorkloadReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        userWorkloadReport.addComponent(ELabel.fontIcon(FontAwesome.BAR_CHART).withStyleName("icon-38px"));
        A userWorkloadReportLink = new A(ProjectLinkBuilder.generateUsersWorkloadReportLink())
                .appendText(AppContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD));
        userWorkloadReport.addComponent(ELabel.h3(userWorkloadReportLink.write()).withWidthUndefined());
        userWorkloadReport.addComponent(new ELabel(AppContext.getMessage(ProjectReportI18nEnum.REPORT_USERS_WORKLOAD_HELP)).withFullWidth());
        content.addComponent(userWorkloadReport);

        MVerticalLayout timesheetReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        timesheetReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        timesheetReport.addComponent(ELabel.fontIcon(FontAwesome.CLOCK_O).withStyleName("icon-38px"));
        A timesheetReportLink = new A(ProjectLinkBuilder.generateTimesheetReportLink())
                .appendText(AppContext.getMessage(ProjectReportI18nEnum.REPORT_TIMESHEET));
        timesheetReport.addComponent(ELabel.h3(timesheetReportLink.write()).withWidthUndefined());
        timesheetReport.addComponent(new ELabel(AppContext.getMessage(ProjectReportI18nEnum.REPORT_TIMESHEET_HELP))
                .withFullWidth());
        content.addComponent(timesheetReport);
        body.addComponent(content);
    }

    @Override
    public void addView(PageView view) {
        body.removeAllComponents();
        body.addComponent(view);
    }
}
