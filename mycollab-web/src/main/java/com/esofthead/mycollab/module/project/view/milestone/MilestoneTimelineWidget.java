package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.hp.gagawa.java.elements.*;
import com.vaadin.data.Property;
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
public class MilestoneTimelineWidget extends MVerticalLayout {
    private CssLayout timelineContainer;

    public void display() {
        this.setWidth("100%");
        this.addStyleName("tm-container");

        MHorizontalLayout headerLayout = new MHorizontalLayout();
        ELabel titleLbl = new ELabel("Phase Timeline").withStyleName(ValoTheme.LABEL_H2);
        titleLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);

        final CheckBox includeClosedMilestone = new CheckBox("Include closed phase");
        includeClosedMilestone.setValue(false);
        includeClosedMilestone.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                displayTimelines(includeClosedMilestone.getValue());
            }
        });
        headerLayout.with(titleLbl, includeClosedMilestone).expand(titleLbl).withAlign(includeClosedMilestone, Alignment.MIDDLE_RIGHT);

        this.addComponent(headerLayout);
        timelineContainer = new CssLayout();
        timelineContainer.setWidth("100%");
        this.addComponent(timelineContainer);
        timelineContainer.addStyleName("tm-wrapper");
        displayTimelines(false);
    }

    private void displayTimelines(boolean includeClosedMilestone) {
        timelineContainer.removeAllComponents();
        Ul ul = new Ul().setCSSClass("timeline");
        MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
        searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField(Milestone.Field.enddate.name(), "ASC")));
        MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
        List<SimpleMilestone> milestones = milestoneService.findPagableListByCriteria(new SearchRequest<>(searchCriteria, 0, Integer.MAX_VALUE));
        for (SimpleMilestone milestone : milestones) {
            if (!includeClosedMilestone) {
                if (MilestoneStatus.Closed.name().equals(milestone.getStatus())) {
                    continue;
                }
            }
            Li li = new Li();
            if (MilestoneStatus.Closed.name().equals(milestone.getStatus())) {
                li.setCSSClass("li closed");
            } else if (MilestoneStatus.InProgress.name().equals(milestone.getStatus())) {
                li.setCSSClass("li inprogress");
            } else if (MilestoneStatus.Future.name().equals(milestone.getStatus())) {
                li.setCSSClass("li future");
            }
            Div timestampDiv = new Div().setCSSClass("timestamp");

            int openAssignments = milestone.getNumOpenBugs() + milestone.getNumOpenTasks();
            int totalAssignments = milestone.getNumBugs() + milestone.getNumTasks();
            if (totalAssignments > 0) {
                timestampDiv.appendChild(new Span().setCSSClass("author").appendText((totalAssignments -
                        openAssignments) * 100 / totalAssignments + "%"));
            } else {
                timestampDiv.appendChild(new Span().setCSSClass("author").appendText("100%"));
            }

            if (milestone.getEnddate() == null) {
                timestampDiv.appendChild(new Span().setCSSClass("date").appendText("No date set"));
            } else {
                timestampDiv.appendChild(new Span().setCSSClass("date").appendText(AppContext.formatDate(milestone.getEnddate())));
            }
            li.appendChild(timestampDiv);

            Div statusDiv = new Div();
            statusDiv.setCSSClass("status").appendChild(new A(ProjectLinkBuilder.generateMilestonePreviewFullLink
                    (milestone.getProjectid(), milestone.getId())).appendText(StringUtils.trim(milestone.getName(), 30, true)));
            li.appendChild(statusDiv);
            ul.appendChild(li);
        }

        timelineContainer.addComponent(new ELabel(ul.write(), ContentMode.HTML).withWidthUndefined());
    }
}
