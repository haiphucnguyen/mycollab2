/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractView implements
		MilestoneListView {
	private static final long serialVersionUID = 1L;

	private VerticalLayout inProgressContainer;

	private VerticalLayout futureContainer;

	private VerticalLayout closeContainer;

	public MilestoneListViewImpl() {

		HorizontalLayout header = new HorizontalLayout();
		Label titleLbl = new Label("Milestones");
		header.addComponent(titleLbl);
		header.setExpandRatio(titleLbl, 1.0f);

		Button createBtn = new Button("Create", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new MilestoneEvent.GotoAdd(MilestoneListViewImpl.this,
								null));
			}
		});
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(createBtn);
		this.addComponent(header);

		HorizontalLayout bodyContent = new HorizontalLayout();
		bodyContent.setMargin(true);
		bodyContent.setSpacing(true);

		closeContainer = new VerticalLayout();
		bodyContent.addComponent(closeContainer);
		bodyContent.setExpandRatio(closeContainer, 1f);

		inProgressContainer = new VerticalLayout();
		bodyContent.addComponent(inProgressContainer);
		bodyContent.setExpandRatio(inProgressContainer, 1f);

		futureContainer = new VerticalLayout();
		bodyContent.addComponent(futureContainer);
		bodyContent.setExpandRatio(futureContainer, 1f);

		this.addComponent(bodyContent);
	}

	@Override
	public void displayMilestones(List<SimpleMilestone> milestones) {
		inProgressContainer.removeAllComponents();
		inProgressContainer.addComponent(new Label("In Progress"));

		futureContainer.removeAllComponents();
		futureContainer.addComponent(new Label("Future"));

		closeContainer.removeAllComponents();
		closeContainer.addComponent(new Label("Close"));

		for (SimpleMilestone milestone : milestones) {
			if (SimpleMilestone.STATUS_INPROGRESS.equals(milestone.getStatus())) {
				inProgressContainer
						.addComponent(constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_FUTURE.equals(milestone
					.getStatus())) {
				futureContainer.addComponent(constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_CLOSE.equals(milestone
					.getStatus())) {
				closeContainer.addComponent(constructMilestoneBox(milestone));
			}
		}

	}

	private ComponentContainer constructMilestoneBox(
			final SimpleMilestone milestone) {
		VerticalLayout layout = new VerticalLayout();
		Button milestoneLink = new Button(milestone.getName(),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoRead(
										MilestoneListViewImpl.this, milestone
												.getId()));
					}
				});
		milestoneLink.setStyleName("link");
		milestone.setDescription(milestone.getDescription());
		layout.addComponent(milestoneLink);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 4);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getStartdate())),
				"Start Date", 0, 0);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getEnddate())),
				"End Date", 0, 1);

		layoutHelper.addComponent(new Label(milestone.getNumOpenTasks() + "/"
				+ milestone.getNumTasks()), "Tasks", 0, 2);

		layoutHelper.addComponent(new Label(milestone.getNumOpenBugs() + "/"
				+ milestone.getNumBugs()), "Bugs", 0, 3);

		layout.addComponent(layoutHelper.getLayout());

		return layout;
	}
}
