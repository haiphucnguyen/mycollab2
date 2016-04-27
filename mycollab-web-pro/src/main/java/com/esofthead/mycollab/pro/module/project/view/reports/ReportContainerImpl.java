package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
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
        body.with(ELabel.h2(FontAwesome.PIE_CHART.getHtml() + " Reports"));
        CssLayout content = new CssLayout();
        content.setStyleName(UIConstants.FLEX_DISPLAY);
        this.addComponent(content);

        MVerticalLayout standupConsole = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        standupConsole.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        standupConsole.addComponent(ELabel.fontIcon(FontAwesome.LEGAL).withStyleName("icon-38px"));
        A standupReportLink = new A(ProjectLinkBuilder.generateStandupDashboardLink())
                .appendText("Standup Report");
        standupConsole.addComponent(ELabel.h3(standupReportLink.write()).withWidthUndefined());
        standupConsole.addComponent(new ELabel("Your daily scrum which asks three questions What you did yesterday?, " +
                "What you will do today? and Do you have roadblocks? in the StandUp meeting which should not " +
                "exceed 15 minutes.").withWidth("100%"));
        content.addComponent(standupConsole);

        MVerticalLayout hoursWeeklyReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        hoursWeeklyReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        hoursWeeklyReport.addComponent(ELabel.fontIcon(FontAwesome.BALANCE_SCALE).withStyleName("icon-38px"));
        A hoursWeeklyReportLink = new A(ProjectLinkBuilder.generateHoursWeeklyReportLink())
                .appendText("Hours Weekly Report");
        hoursWeeklyReport.addComponent(ELabel.h3(hoursWeeklyReportLink.write()).withWidthUndefined());
        hoursWeeklyReport.addComponent(new ELabel("Your members hours weekly report. Keep your project in time and budget")
                .withWidth("100%"));
        content.addComponent(hoursWeeklyReport);

        MVerticalLayout userWorkloadReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        userWorkloadReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        userWorkloadReport.addComponent(ELabel.fontIcon(FontAwesome.BAR_CHART).withStyleName("icon-38px"));
        A userWorkloadReportLink = new A(ProjectLinkBuilder.generateUsersWorkloadReportLink())
                .appendText("User Workload Report");
        userWorkloadReport.addComponent(ELabel.h3(userWorkloadReportLink.write()).withWidthUndefined());
        userWorkloadReport.addComponent(new ELabel("Preview your user workloads and adjust to keep your project safe," +
                " and your members are happy").withWidth("100%"));
        content.addComponent(userWorkloadReport);

        MVerticalLayout timesheetReport = new MVerticalLayout().withWidth("300px").withStyleName("member-block");
        timesheetReport.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        timesheetReport.addComponent(ELabel.fontIcon(FontAwesome.CLOCK_O).withStyleName("icon-38px"));
        A timesheetReportLink = new A(ProjectLinkBuilder.generateTimesheetReportLink())
                .appendText("Timesheet Report");
        timesheetReport.addComponent(ELabel.h3(timesheetReportLink.write()).withWidthUndefined());
        timesheetReport.addComponent(new ELabel("Keep project's efforts and cost in various charts to give you an " +
                "insight view of how do you spend money and efforts")
                .withWidth("100%"));
        content.addComponent(timesheetReport);
        body.addComponent(content);
    }

    @Override
    public void addView(PageView view) {
        body.removeAllComponents();
        body.addComponent(view);
    }
}
