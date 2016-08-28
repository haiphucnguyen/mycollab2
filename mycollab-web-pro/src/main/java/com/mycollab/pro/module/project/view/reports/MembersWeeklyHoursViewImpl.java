package com.mycollab.pro.module.project.view.reports;

import com.hp.gagawa.java.elements.A;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.module.project.ui.components.ProjectMemberLink;
import com.mycollab.pro.module.project.ui.components.ProjectMultiSelect;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.WeeklyCalendarFieldExp;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.vaadin.alump.distributionbar.DistributionBar;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class MembersWeeklyHoursViewImpl extends AbstractPageView implements MembersWeeklyHoursView {
    private MVerticalLayout searchResultLayout;

    public MembersWeeklyHoursViewImpl() {
        withSpacing(true);
    }

    @Override
    public void display() {
        removeAllComponents();
        with(ELabel.h2(FontAwesome.BALANCE_SCALE.getHtml() + " " + AppContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY)));
        MVerticalLayout container = new MVerticalLayout().withStyleName(WebUIConstants.BOX);
        with(container);

        GridLayout searchLayout = new GridLayout(5, 1);
        searchLayout.setSpacing(true);
        searchLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        List<SimpleProject> projects = projectService.getProjectsUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        final ProjectMultiSelect projectsSelection = new ProjectMultiSelect(projects);
        searchLayout.addComponent(new ELabel(AppContext.getMessage(ProjectI18nEnum.LIST)).withStyleName(WebUIConstants.META_COLOR), 0, 0);
        searchLayout.addComponent(projectsSelection, 1, 0);
        searchLayout.addComponent(new ELabel(AppContext.getMessage(DayI18nEnum.OPT_WEEK)).withStyleName(WebUIConstants.META_COLOR), 2,
                0);
        final WeeklyCalendarFieldExp dateFieldExt = new WeeklyCalendarFieldExp();
        dateFieldExt.setValue(new DateTime().toDate());

        searchLayout.addComponent(dateFieldExt, 3, 0);
        container.with(searchLayout);
        MButton searchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> {
            Collection<SimpleProject> selectedProjects = (Collection<SimpleProject>) projectsSelection.getValue();
            if (CollectionUtils.isEmpty(selectedProjects)) {
                NotificationUtil.showErrorNotification("You must select at least one project");
            } else {
                buildHourlyProjectsReport(selectedProjects, dateFieldExt.getValue());
            }
        }).withStyleName(WebUIConstants.BUTTON_ACTION);
        searchLayout.addComponent(searchBtn, 4, 0);
        with(new MCssLayout(new MHorizontalLayout(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS)))
                .withWidth("150px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-1")).withStyleName("alump-dbar"));
        with(new MCssLayout(new MHorizontalLayout(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS)))
                .withWidth("150px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-2")).withStyleName("alump-dbar"));
        with(new MCssLayout(new MHorizontalLayout(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.OPT_REMAIN_HOURS)))
                .withWidth("150px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-3")).withStyleName("alump-dbar"));
        searchResultLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false));
        with(searchResultLayout);
    }

    private void buildHourlyProjectsReport(Collection<SimpleProject> selectedProjects, Date dateInWeek) {
        searchResultLayout.removeAllComponents();
        for (SimpleProject project : selectedProjects) {
            MVerticalLayout contentLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false));
            Component projectLogo = ProjectAssetsUtil.buildProjectLogo(project.getShortname(), project.getId(), project.getAvatarid(), 32);
            A projectDiv = new A(ProjectLinkBuilder.generateProjectFullLink(project.getId())).appendText(project.getName());
            ELabel projectLbl = ELabel.h3(projectDiv.write()).withStyleName(UIConstants.TEXT_ELLIPSIS).withFullWidth();
            contentLayout.with(new MHorizontalLayout(projectLogo, projectLbl).expand(projectLbl).withFullWidth());
            ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
            DateTime now = new DateTime(dateInWeek);
            Date start = now.dayOfWeek().withMinimumValue().toDate();
            Date end = now.dayOfWeek().withMaximumValue().toDate();
            List<SimpleProjectMember> members = projectMemberService.findMembersHourlyInProject(project.getId(),
                    AppContext.getAccountId(), start, end);

            for (SimpleProjectMember member : members) {
                MHorizontalLayout memberLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, true));
                ProjectMemberLink assignUserLink = new ProjectMemberLink(member.getProjectid(), member.getUsername(),
                        member.getMemberAvatarId(), member.getDisplayName());
                assignUserLink.setWidth("120px");
                memberLayout.with(assignUserLink);
                Double billableHours = member.getTotalBillableLogTime();
                Double nonBillableHours = member.getTotalNonBillableLogTime();
                DistributionBar memberBarDist = new DistributionBar(3);
                memberBarDist.setZeroSizedVisible(false);

                memberBarDist.setPartTooltip(0, AppContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
                memberBarDist.setPartSize(0, member.getTotalBillableLogTime());
                memberBarDist.setPartTooltip(1, AppContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
                memberBarDist.setPartSize(1, member.getTotalNonBillableLogTime());
                if ((billableHours + nonBillableHours) > 40) {
                    memberBarDist.setPartSize(2, 0);
                    memberBarDist.setWidth((billableHours + nonBillableHours) * 10 + "px");
                } else {
                    memberBarDist.setPartSize(2, 40 - (billableHours + nonBillableHours));
                    memberBarDist.setWidth("400px");
                }
                memberBarDist.setPartTooltip(2, AppContext.getMessage(TimeTrackingI18nEnum.OPT_REMAIN_HOURS));
                memberLayout.with(memberBarDist);
                contentLayout.with(memberLayout);
            }
            searchResultLayout.with(contentLayout);
        }
    }
}
