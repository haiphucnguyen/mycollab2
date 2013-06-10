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
import com.esofthead.mycollab.utils.StringUtils;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
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
import com.vaadin.ui.ProgressIndicator;
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
		// header.setMargin(true, true, false, true);
		header.setWidth("100%");
		header.addComponent(titleLbl);
		header.setComponentAlignment(titleLbl, Alignment.MIDDLE_LEFT);

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
		this.createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(this.createBtn);
		header.setComponentAlignment(this.createBtn, Alignment.MIDDLE_RIGHT);
		headerWrapper.addComponent(header);
		this.addComponent(headerWrapper);

		final CustomLayout bodyContent = new CustomLayout("milestoneView");
		bodyContent.setWidth("100%");
		bodyContent.setStyleName("milestone-view");

		final HorizontalLayout closedHeaderLayout = new HorizontalLayout();
		closedHeaderLayout.setSpacing(true);
		final Embedded embeddClosed = new Embedded(null, new ThemeResource(
				"icons/16/project/phase_closed.png"));
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
		final Embedded embeddInProgress = new Embedded(null, new ThemeResource(
				"icons/16/project/phase_progress.png"));
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
		final Embedded embeddFuture = new Embedded(null, new ThemeResource(
				"icons/16/project/phase_future.png"));
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

		final VerticalLayout linkWrapper = new VerticalLayout();
		linkWrapper.setWidth("100%");
		linkWrapper.setHeight("32px");
		final String milestoneLinkCaption = StringUtils.trimString(
				milestone.getName(), 45, true);
		final Button milestoneLink = CommonUIFactory.createButtonTooltip(
				milestoneLinkCaption, milestone.getName(),
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
		milestoneLink.addStyleName("medium-text");
		milestoneLink.addStyleName("bold");
		milestoneLink.addStyleName(UIConstants.WORD_WRAP);
		milestoneLink.setWidth("100%");
		milestone.setDescription(milestone.getDescription());
		linkWrapper.addComponent(milestoneLink);

		layout.addComponent(linkWrapper);

		final GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1,
				5, "100%", "60px");
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getStartdate())),
				"Start Date", 0, 0, Alignment.MIDDLE_LEFT);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getEnddate())),
				"End Date", 0, 1, Alignment.MIDDLE_LEFT);

		layoutHelper.addComponent(new ProjectUserLink(milestone.getOwner(), "",
				false, true), "Assignee", 0, 2, Alignment.MIDDLE_LEFT);

		final HorizontalLayout taskComp = new HorizontalLayout();
		taskComp.setWidth("100%");
		taskComp.setSpacing(true);
		final ProgressIndicator progressTask = new ProgressIndicator(new Float(
				(float) (milestone.getNumTasks() - milestone.getNumOpenTasks())
						/ milestone.getNumTasks()));
		progressTask.setPollingInterval(1000000000);
		progressTask.setWidth("100%");
		taskComp.addComponent(progressTask);
		final Label taskNumber = new Label("(" + milestone.getNumOpenTasks()
				+ "/" + milestone.getNumTasks() + ")");
		taskNumber.setWidth("90px");
		taskComp.addComponent(taskNumber);
		taskComp.setExpandRatio(progressTask, 1.0f);
		// taskComp.setComponentAlignment(taskNumber, Alignment.MIDDLE_CENTER);

		layoutHelper.addComponent(taskComp, "Tasks", 0, 3,
				Alignment.MIDDLE_LEFT);

		final HorizontalLayout bugComp = new HorizontalLayout();
		bugComp.setWidth("100%");
		bugComp.setSpacing(true);
		final ProgressIndicator progressBug = new ProgressIndicator(new Float(
				(float) (milestone.getNumBugs() - milestone.getNumOpenBugs())
						/ milestone.getNumBugs()));
		progressBug.setPollingInterval(1000000000);
		progressBug.setWidth("100%");
		bugComp.addComponent(progressBug);
		final Label bugNumber = new Label("(" + milestone.getNumOpenBugs()
				+ "/" + milestone.getNumBugs() + ")");
		bugNumber.setWidth("90px");
		bugComp.addComponent(bugNumber);
		bugComp.setExpandRatio(progressBug, 1.0f);

		layoutHelper.addComponent(bugComp, "Bugs", 0, 4, Alignment.MIDDLE_LEFT);
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
				this.inProgressContainer.addComponent(this
						.constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_FUTURE.equals(milestone
					.getStatus())) {
				this.futureContainer.addComponent(this
						.constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_CLOSE.equals(milestone
					.getStatus())) {
				this.closeContainer.addComponent(this
						.constructMilestoneBox(milestone));
			}
		}

	}
}
