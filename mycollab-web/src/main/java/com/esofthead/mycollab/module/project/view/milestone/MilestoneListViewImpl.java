/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ProgressBar;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractView implements
        MilestoneListView {
    private static final long serialVersionUID = 1L;

    private final VerticalLayout inProgressContainer;

    private final VerticalLayout futureContainer;

    private final VerticalLayout closeContainer;
    private final Button createBtn;

    public MilestoneListViewImpl() {
        this.setMargin(true);
        final CssLayout headerWrapper = new CssLayout();
        headerWrapper.setWidth("100%");
        headerWrapper.addStyleName("milestonelist-header");
        final HorizontalLayout header = new HorizontalLayout();
        final Label titleLbl = new Label("Phases");
        titleLbl.addStyleName("h2");
        header.setWidth("100%");
        final Embedded icon = new Embedded();
        icon.setSource(MyCollabResource
                .newResource("icons/24/project/phase.png"));
        header.addComponent(icon);
        header.setComponentAlignment(icon, Alignment.MIDDLE_LEFT);
        header.addComponent(titleLbl);
        header.setComponentAlignment(titleLbl, Alignment.MIDDLE_LEFT);
        header.setExpandRatio(titleLbl, 1.0f);
        header.setSpacing(true);

        this.createBtn = new Button(
                LocalizationHelper.getMessage(TaskI18nEnum.NEW_PHASE_ACTION),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoAdd(
                                        MilestoneListViewImpl.this, null));
                    }
                });
        this.createBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        this.createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(this.createBtn);
        header.setComponentAlignment(this.createBtn, Alignment.MIDDLE_RIGHT);
        headerWrapper.addComponent(header);
        this.addComponent(headerWrapper);

        final CustomLayout bodyContent = CustomLayoutLoader
                .createLayout("milestoneView");
        bodyContent.setWidth("100%");
        bodyContent.setStyleName("milestone-view");

        final HorizontalLayout closedHeaderLayout = new HorizontalLayout();
        closedHeaderLayout.setSpacing(true);
        final Embedded embeddClosed = new Embedded(null,
                MyCollabResource
                        .newResource("icons/16/project/phase_closed.png"));
        closedHeaderLayout.addComponent(embeddClosed);
        closedHeaderLayout.setComponentAlignment(embeddClosed,
                Alignment.MIDDLE_CENTER);
        final Label closedHeader = new Label("Closed");
        closedHeader.setSizeUndefined();
        closedHeaderLayout.addComponent(closedHeader);
        closedHeaderLayout.setComponentAlignment(closedHeader,
                Alignment.MIDDLE_CENTER);

        bodyContent.addComponent(closedHeaderLayout, "closed-header");
        this.closeContainer = new VerticalLayout();
        this.closeContainer.setWidth("100%");
        bodyContent.addComponent(this.closeContainer, "closed-milestones");

        final HorizontalLayout inProgressHeaderLayout = new HorizontalLayout();
        inProgressHeaderLayout.setSpacing(true);
        final Embedded embeddInProgress = new Embedded(null,
                MyCollabResource
                        .newResource("icons/16/project/phase_progress.png"));
        inProgressHeaderLayout.addComponent(embeddInProgress);
        inProgressHeaderLayout.setComponentAlignment(embeddInProgress,
                Alignment.MIDDLE_CENTER);
        final Label inProgressHeader = new Label("In Progress");
        inProgressHeader.setSizeUndefined();
        inProgressHeaderLayout.addComponent(inProgressHeader);
        inProgressHeaderLayout.setComponentAlignment(inProgressHeader,
                Alignment.MIDDLE_CENTER);

        bodyContent.addComponent(inProgressHeaderLayout, "in-progress-header");
        this.inProgressContainer = new VerticalLayout();
        this.inProgressContainer.setWidth("100%");
        bodyContent.addComponent(this.inProgressContainer,
                "in-progress-milestones");

        final HorizontalLayout futureHeaderLayout = new HorizontalLayout();
        futureHeaderLayout.setSpacing(true);
        final Embedded embeddFuture = new Embedded(null,
                MyCollabResource
                        .newResource("icons/16/project/phase_future.png"));
        futureHeaderLayout.addComponent(embeddFuture);
        futureHeaderLayout.setComponentAlignment(embeddFuture,
                Alignment.MIDDLE_CENTER);
        final Label futureHeader = new Label("Future");
        futureHeader.setSizeUndefined();
        futureHeaderLayout.addComponent(futureHeader);
        futureHeaderLayout.setComponentAlignment(futureHeader,
                Alignment.MIDDLE_CENTER);

        bodyContent.addComponent(futureHeaderLayout, "future-header");
        this.futureContainer = new VerticalLayout();
        this.futureContainer.setWidth("100%");
        bodyContent.addComponent(this.futureContainer, "future-milestones");

        this.addComponent(bodyContent);
        this.setExpandRatio(bodyContent, 1.0f);
    }

    private ComponentContainer constructMilestoneBox(
            final SimpleMilestone milestone) {
        final CssLayout layout = new CssLayout();
        layout.addStyleName(UIConstants.MILESTONE_BOX);
        layout.setWidth("100%");

        final Button milestoneLink = new Button(milestone.getName(),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new MilestoneEvent.GotoRead(
                                        MilestoneListViewImpl.this, milestone
                                                .getId()));
                    }
                });
        milestoneLink.setStyleName("link");
        milestoneLink.addStyleName("bold");
        milestoneLink.addStyleName(UIConstants.WORD_WRAP);
        milestoneLink.setWidth("100%");
        milestone.setDescription(milestone.getDescription());

        layout.addComponent(milestoneLink);

        final GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1,
                5, "100%", "60px");
        layoutHelper.addComponent(
                new Label(AppContext.formatDate(milestone.getStartdate(),
                        "<<Not Set>>")), "Start Date", 0, 0,
                Alignment.MIDDLE_LEFT);
        layoutHelper.addComponent(
                new Label(AppContext.formatDate(milestone.getEnddate(),
                        "<<Not Set>>")), "End Date", 0, 1,
                Alignment.MIDDLE_LEFT);

        layoutHelper.addComponent(new ProjectUserLink(milestone.getOwner(),
                milestone.getOwnerAvatarId(), milestone.getOwnerFullName(),
                true, true), "Assignee", 0, 2, Alignment.MIDDLE_LEFT);

        final ProgressBar progressTask = new ProgressBar(
                milestone.getNumTasks(), milestone.getNumOpenTasks());
        progressTask.setWidth("100%");

        layoutHelper.addComponent(progressTask, "Tasks", 0, 3,
                Alignment.MIDDLE_LEFT);

        final ProgressBar progressBug = new ProgressBar(milestone.getNumBugs(),
                milestone.getNumOpenBugs());
        progressBug.setWidth("100%");

        layoutHelper.addComponent(progressBug, "Bugs", 0, 4,
                Alignment.MIDDLE_LEFT);
        final GridLayout milestoneInfoLayout = layoutHelper.getLayout();
        milestoneInfoLayout.setWidth("100%");
        milestoneInfoLayout.setMargin(false);
        milestoneInfoLayout.setSpacing(true);
        layout.addComponent(milestoneInfoLayout);

        return layout;
    }

    @Override
    public void displayMilestones(final List<SimpleMilestone> milestones) {
        this.createBtn.setEnabled(CurrentProjectVariables
                .canWrite(ProjectRolePermissionCollections.MILESTONES));

        this.inProgressContainer.removeAllComponents();
        this.futureContainer.removeAllComponents();
        this.closeContainer.removeAllComponents();

        for (final SimpleMilestone milestone : milestones) {
            if (SimpleMilestone.STATUS_INPROGRESS.equals(milestone.getStatus())) {
                this.inProgressContainer.addComponent(new LazyLoadWrapper(this
                        .constructMilestoneBox(milestone)));
            } else if (SimpleMilestone.STATUS_FUTURE.equals(milestone
                    .getStatus())) {
                this.futureContainer.addComponent(new LazyLoadWrapper(this
                        .constructMilestoneBox(milestone)));
            } else if (SimpleMilestone.STATUS_CLOSE.equals(milestone
                    .getStatus())) {
                this.closeContainer.addComponent(new LazyLoadWrapper(this
                        .constructMilestoneBox(milestone)));
            }
        }

    }
}
