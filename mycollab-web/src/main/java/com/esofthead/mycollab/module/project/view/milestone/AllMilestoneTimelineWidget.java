package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.UserDashboardView;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.hp.gagawa.java.elements.*;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class AllMilestoneTimelineWidget extends MVerticalLayout {
    private List<SimpleMilestone> milestones;
    private CssLayout timelineContainer;

    public void display() {
        this.withMargin(new MarginInfo(true, false, true, false));
        this.setWidth("100%");
        this.addStyleName("tm-container");

        MHorizontalLayout headerLayout = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true));
        ELabel titleLbl = new ELabel("Phase Timeline").withStyleName(ValoTheme.LABEL_H2);
        titleLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);

        final CheckBox includeNoDateSet = new CheckBox("No date set");
        includeNoDateSet.setValue(false);

        final CheckBox includeClosedMilestone = new CheckBox("Closed phase");
        includeClosedMilestone.setValue(false);

        includeNoDateSet.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                displayTimelines(includeNoDateSet.getValue(), includeClosedMilestone.getValue());
            }
        });
        includeClosedMilestone.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                displayTimelines(includeNoDateSet.getValue(), includeClosedMilestone.getValue());
            }
        });
        headerLayout.with(titleLbl, includeNoDateSet, includeClosedMilestone).expand(titleLbl).withAlign(includeNoDateSet, Alignment
                .MIDDLE_RIGHT).withAlign(includeClosedMilestone, Alignment.MIDDLE_RIGHT);

        MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
        UserDashboardView userDashboardView = UIUtils.getRoot(this, UserDashboardView.class);
        searchCriteria.setProjectIds(new SetSearchField<>(userDashboardView.getInvoledProjKeys()));
        searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField(Milestone.Field.enddate.name(), "ASC")));
        MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
        milestones = milestoneService.findPagableListByCriteria(new SearchRequest<>(searchCriteria, 0, Integer.MAX_VALUE));

        this.addComponent(headerLayout);
        timelineContainer = new CssLayout();
        timelineContainer.setWidth("100%");
        this.addComponent(timelineContainer);
        timelineContainer.addStyleName("tm-wrapper");
        displayTimelines(false, false);
    }

    private void displayTimelines(boolean includeNoDateSet, boolean includeClosedMilestone) {
        timelineContainer.removeAllComponents();
        Ul ul = new Ul().setCSSClass("timeline");

        for (SimpleMilestone milestone : milestones) {
            if (!includeClosedMilestone) {
                if (OptionI18nEnum.MilestoneStatus.Closed.name().equals(milestone.getStatus())) {
                    continue;
                }
            }
            if (!includeNoDateSet) {
                if (milestone.getEnddate() == null) {
                    continue;
                }
            }
            Li li = new Li();
            if (OptionI18nEnum.MilestoneStatus.Closed.name().equals(milestone.getStatus())) {
                li.setCSSClass("li closed");
            } else if (OptionI18nEnum.MilestoneStatus.InProgress.name().equals(milestone.getStatus())) {
                li.setCSSClass("li inprogress");
            } else if (OptionI18nEnum.MilestoneStatus.Future.name().equals(milestone.getStatus())) {
                li.setCSSClass("li future");
            }

            Div timestampDiv = new Div().setCSSClass("timestamp");

            int openAssignments = milestone.getNumOpenBugs() + milestone.getNumOpenTasks();
            int totalAssignments = milestone.getNumBugs() + milestone.getNumTasks();
            if (totalAssignments > 0) {
                timestampDiv.appendChild(new Span().appendText((totalAssignments -
                        openAssignments) * 100 / totalAssignments + "%"));
            } else {
                timestampDiv.appendChild(new Span().appendText("100%"));
            }

            if (milestone.getEnddate() == null) {
                timestampDiv.appendChild(new Span().setCSSClass("date").appendText("No date set"));
            } else {
                if (milestone.isOverdue()) {
                    timestampDiv.appendChild(new Span().setCSSClass("date overdue").appendText(AppContext.formatDate
                            (milestone.getEnddate()) + " (Due in " + AppContext.formatDuration(milestone.getEnddate()) + ")"));
                } else {
                    timestampDiv.appendChild(new Span().setCSSClass("date").appendText(AppContext.formatDate(milestone.getEnddate())));
                }
            }
            li.appendChild(timestampDiv);


            A projectDiv = new A(ProjectLinkBuilder.generateProjectFullLink(milestone.getProjectid())).appendText
                    (FontAwesome.BUILDING_O.getHtml() + " " + StringUtils.trim(milestone.getProjectName(), 30, true));
            A milestoneDiv = new A(ProjectLinkBuilder.generateMilestonePreviewFullLink
                    (milestone.getProjectid(), milestone.getId())).appendText(ProjectAssetsManager.getAsset
                    (ProjectTypeConstants.MILESTONE).getHtml() + " " + StringUtils.trim(milestone.getName(), 30, true));
            Div statusDiv = new Div().setCSSClass("status").appendChild(projectDiv, milestoneDiv);
            li.appendChild(statusDiv);
            ul.appendChild(li);
        }

        timelineContainer.addComponent(new ELabel(ul.write(), ContentMode.HTML).withWidthUndefined());
    }
}
