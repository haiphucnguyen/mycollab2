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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
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

	public MilestoneListViewImpl() {

		HorizontalLayout header = new HorizontalLayout();
		Label titleLbl = new Label("Milestones");
		titleLbl.addStyleName("h2");
		header.setMargin(true, true, false, true);
		header.setWidth("100%");
		header.addComponent(titleLbl);
		header.setComponentAlignment(titleLbl, Alignment.MIDDLE_LEFT);

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
		header.setComponentAlignment(createBtn, Alignment.MIDDLE_RIGHT);
		this.addComponent(header);

		HorizontalLayout bodyContent = new HorizontalLayout();
		bodyContent.setMargin(true);
		bodyContent.setSpacing(true);
		bodyContent.setWidth("100%");

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
		Label inProgressHeader = new Label("In Progress");
		inProgressHeader.setSizeUndefined();
		inProgressContainer.addComponent(inProgressHeader);
		inProgressContainer.setComponentAlignment(inProgressHeader,
				Alignment.MIDDLE_CENTER);

		futureContainer.removeAllComponents();
		Label futureHeader = new Label("Future");
		futureHeader.setSizeUndefined();
		futureContainer.addComponent(futureHeader);
		futureContainer.setComponentAlignment(futureHeader,
				Alignment.MIDDLE_CENTER);

		closeContainer.removeAllComponents();
		Label closeHeader = new Label("Close");
		closeHeader.setSizeUndefined();
		closeContainer.addComponent(closeHeader);
		closeContainer.setComponentAlignment(closeHeader,
				Alignment.MIDDLE_CENTER);

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
		CssLayout layout = new CssLayout();
		layout.addStyleName(UIConstants.MILESTONE_BOX);
		layout.setWidth("100%");
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
		milestoneLink.addStyleName("medium-text");
		milestoneLink.addStyleName("bold");
		milestoneLink.addStyleName(UIConstants.WORD_WRAP);
		milestone.setDescription(milestone.getDescription());
		layout.addComponent(milestoneLink);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 4);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getStartdate())),
				"Start Date", 0, 0, Alignment.MIDDLE_LEFT);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getEnddate())),
				"End Date", 0, 1, Alignment.MIDDLE_LEFT);

		layoutHelper.addComponent(new Label(milestone.getNumOpenTasks() + "/"
				+ milestone.getNumTasks()), "Tasks", 0, 2,
				Alignment.MIDDLE_LEFT);

		layoutHelper.addComponent(new Label(milestone.getNumOpenBugs() + "/"
				+ milestone.getNumBugs()), "Bugs", 0, 3, Alignment.MIDDLE_LEFT);
		GridLayout milestoneInfoLayout = layoutHelper.getLayout();
		milestoneInfoLayout.setMargin(false);
		layout.addComponent(milestoneInfoLayout);

		return layout;
	}
}
