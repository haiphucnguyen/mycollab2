package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.ProjectInformationComponent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectSummaryViewImpl extends AbstractView implements
		ProjectSummaryView {

	private ProjectActivityStreamComponent activityPanel;
	private ProjectInformationComponent prjView;
	private ProjectMembersWidget membersWidget;
	private ProjectTaskStatusComponent highlightWidget;
	private ProjectMessageListComponent messageWidget;

	public ProjectSummaryViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		prjView = new ProjectInformationComponent();
		this.addComponent(prjView);

		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		this.addComponent(layout);

		VerticalLayout leftPanel = new VerticalLayout();

		messageWidget = new ProjectMessageListComponent();
		leftPanel.addComponent(new LazyLoadWrapper(messageWidget));

		activityPanel = new ProjectActivityStreamComponent();
		leftPanel.addComponent(new LazyLoadWrapper(activityPanel));
		layout.addComponent(leftPanel);

		VerticalLayout rightPanel = new VerticalLayout();
		rightPanel.setSpacing(true);
		layout.addComponent(rightPanel);

		membersWidget = new ProjectMembersWidget();
		highlightWidget = new ProjectTaskStatusComponent();
		rightPanel.addComponent(new LazyLoadWrapper(membersWidget));
		rightPanel.addComponent(new LazyLoadWrapper(highlightWidget));
	}

	@Override
	public void displayDashboard() {
		activityPanel.showProjectFeeds();
		prjView.displayProjectInformation();
		membersWidget.showInformation();
		highlightWidget.showProjectTasksByStatus();
		messageWidget.showLatestMessages();
	}
}
