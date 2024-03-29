package com.mycollab.pro.module.project.view.reports;

import com.hp.gagawa.java.elements.A;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.ProjectReportI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.module.project.ui.components.ProjectMemberLink;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.RangeDateField;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.ItemCaptionGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.addons.ComboBoxMultiselect;
import org.vaadin.alump.distributionbar.DistributionBar;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class MembersWeeklyHoursViewImpl extends AbstractVerticalPageView implements MembersWeeklyHoursView {
    private MVerticalLayout searchResultLayout;

    public MembersWeeklyHoursViewImpl() {
        withSpacing(true);
    }

    @Override
    public void display() {
        removeAllComponents();
        with(ELabel.h2(VaadinIcons.CHART_LINE.getHtml() + " " + UserUIContext.getMessage(ProjectReportI18nEnum.REPORT_HOURS_SPENT)));
        MVerticalLayout contentLayout = new MVerticalLayout().withStyleName(WebThemes.BOX);
        with(contentLayout);

        GridLayout searchLayout = new GridLayout(5, 1);
        searchLayout.setSpacing(true);
        searchLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        List<SimpleProject> projects = projectService.getProjectsUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        ComboBoxMultiselect<SimpleProject> projectsSelection = buildMultiProjectsSelection(projects);
        searchLayout.addComponent(new ELabel(UserUIContext.getMessage(ProjectI18nEnum.LIST)).withStyleName(WebThemes.META_COLOR), 0, 0);
        searchLayout.addComponent(projectsSelection, 1, 0);
        RangeDateField rangeDatesField = new RangeDateField();

        searchLayout.addComponent(rangeDatesField, 2, 0);
        contentLayout.with(searchLayout);
        MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> {
            Collection<SimpleProject> selectedProjects = projectsSelection.getValue();
            if (CollectionUtils.isEmpty(selectedProjects)) {
                NotificationUtil.showErrorNotification("You must select at least one project");
            } else {
                LocalDate[] bounds = rangeDatesField.getBounds();
                buildHourlyProjectsReport(selectedProjects, bounds[0], bounds[1]);
            }
        }).withStyleName(WebThemes.BUTTON_ACTION);
        searchLayout.addComponent(searchBtn, 3, 0);
        with(new MCssLayout(new MHorizontalLayout(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS)))
                .withWidth("180px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-1")).withStyleName("alump-dbar"));
        with(new MCssLayout(new MHorizontalLayout(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS)))
                .withWidth("180px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-2")).withStyleName("alump-dbar"));
        with(new MCssLayout(new MHorizontalLayout(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_REMAIN_HOURS)))
                .withWidth("180px").withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("alump-dbar-part-3")).withStyleName("alump-dbar"));
        searchResultLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false));
        with(searchResultLayout).expand(searchResultLayout);
    }

    private ComboBoxMultiselect<SimpleProject> buildMultiProjectsSelection(List<SimpleProject> projects) {
        ComboBoxMultiselect<SimpleProject> projectsSelection = new ComboBoxMultiselect<>();
        projectsSelection.setWidth("200px");
        projectsSelection.setTextInputAllowed(false);
        projectsSelection.setItems(projects);
        projectsSelection.setItemCaptionGenerator((ItemCaptionGenerator<SimpleProject>) project -> project.getName());
        projectsSelection.setStyleGenerator(project -> WebThemes.TEXT_ELLIPSIS);
        return projectsSelection;
    }

    private void buildHourlyProjectsReport(Collection<SimpleProject> selectedProjects, LocalDate start, LocalDate end) {
        searchResultLayout.removeAllComponents();
        for (SimpleProject project : selectedProjects) {
            MVerticalLayout contentLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false));
            Component projectLogo = ProjectAssetsUtil.projectLogoComp(project.getShortname(), project.getId(), project.getAvatarid(), 32);
            A projectDiv = new A(ProjectLinkGenerator.generateProjectLink(project.getId())).appendText(project.getName());
            ELabel projectLbl = ELabel.h3(projectDiv.write()).withStyleName(WebThemes.TEXT_ELLIPSIS).withFullWidth();
            contentLayout.with(new MHorizontalLayout(projectLogo, projectLbl).expand(projectLbl).withFullWidth());
            ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
            List<SimpleProjectMember> members = projectMemberService.findMembersHourlyInProject(project.getId(),
                    AppUI.getAccountId(), start, end);

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

                memberBarDist.setPartTooltip(0, UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
                memberBarDist.setPartSize(0, member.getTotalBillableLogTime());
                memberBarDist.setPartTooltip(1, UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
                memberBarDist.setPartSize(1, member.getTotalNonBillableLogTime());
                if ((billableHours + nonBillableHours) > 40) {
                    memberBarDist.setPartSize(2, 0);
                    memberBarDist.setWidth((billableHours + nonBillableHours) * 10 + "px");
                } else {
                    memberBarDist.setPartSize(2, 40 - (billableHours + nonBillableHours));
                    memberBarDist.setWidth("400px");
                }
                memberBarDist.setPartTooltip(2, UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_REMAIN_HOURS));
                memberLayout.with(memberBarDist);
                contentLayout.with(memberLayout);
            }
            searchResultLayout.with(contentLayout);
        }
    }
}
