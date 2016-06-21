package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.ProjectMemberLink;
import com.esofthead.mycollab.pro.module.project.ui.components.ProjectMultiSelect;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.PopupDateFieldExt;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.GridLayout;
import org.joda.time.DateTime;
import org.vaadin.alump.distributionbar.DistributionBar;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class MembersWeeklyHoursViewImpl extends AbstractPageView implements MembersWeeklyHoursView {

    @Override
    public void display() {
        removeAllComponents();
        with(ELabel.h2(FontAwesome.BALANCE_SCALE.getHtml() + " " + AppContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_WEEKLY)));
        MVerticalLayout container = new MVerticalLayout().withStyleName(UIConstants.BOX);
        with(container);

        GridLayout searchLayout = new GridLayout(4, 1);
        searchLayout.setSpacing(true);

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        List<SimpleProject> projects = projectService.getProjectsUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        ProjectMultiSelect projectsSelection = new ProjectMultiSelect(projects);
        searchLayout.addComponent(new ELabel("Projects").withStyleName(UIConstants.META_COLOR, UIConstants.TEXT_ALIGN_RIGHT)
                .withWidth("120px"), 0, 0);
        searchLayout.addComponent(projectsSelection, 1, 0);
        searchLayout.addComponent(new ELabel("Week").withStyleName(UIConstants.META_COLOR, UIConstants.TEXT_ALIGN_RIGHT)
                .withWidth("120px"), 2, 0);
        PopupDateFieldExt dateFieldExt = new PopupDateFieldExt();

        searchLayout.addComponent(dateFieldExt, 3, 0);
        container.with(searchLayout);
//        if (CollectionUtils.isNotEmpty(projects)) {
//            for (SimpleProject project : projects) {
//                buildHourlyProjectReport(project);
//            }
//        } else {
//
//        }
    }

    private void buildHourlyProjectReport(SimpleProject project) {
        MVerticalLayout contentLayout = new MVerticalLayout();
        addComponent(contentLayout);
        contentLayout.with(ELabel.h3(project.getName()));
        ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
        DateTime now = new DateTime();
        Date start = now.dayOfWeek().withMinimumValue().toDate();
        Date end = now.dayOfWeek().withMaximumValue().toDate();
        List<SimpleProjectMember> members = projectMemberService.findMembersHourlyInProject(project.getId(), AppContext.getAccountId(),
                start, end);

        for (SimpleProjectMember member : members) {
            MHorizontalLayout memberLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false));
            ProjectMemberLink assignUserLink = new ProjectMemberLink(member.getProjectid(), member.getUsername(),
                    member.getMemberAvatarId(), member.getDisplayName());
            assignUserLink.setWidth("120px");
            memberLayout.with(assignUserLink);
            Double billableHours = member.getTotalBillableLogTime();
            Double nonBillableHours = member.getTotalNonBillableLogTime();
            DistributionBar memberBarDist = new DistributionBar(3);
            memberBarDist.setZeroSizedVisible(false);
            memberBarDist.setWidth("600px");
            memberBarDist.setPartTooltip(0, AppContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
            memberBarDist.setPartSize(0, member.getTotalBillableLogTime());
            memberBarDist.setPartTooltip(1, AppContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
            memberBarDist.setPartSize(1, member.getTotalNonBillableLogTime());
            if ((billableHours + nonBillableHours) > 40) {
                memberBarDist.setPartSize(2, 0);
            } else {
                memberBarDist.setPartSize(2, 40 - (billableHours + nonBillableHours));
            }
            memberBarDist.setPartTooltip(2, AppContext.getMessage(TimeTrackingI18nEnum.OPT_REMAIN_HOURS));
            memberLayout.with(memberBarDist);
            addComponent(memberLayout);
        }


    }
}
