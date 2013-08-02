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

	private final ProjectActivityStreamComponent activityPanel;
	private final ProjectInformationComponent prjView;
	private final ProjectMembersWidget membersWidget;
	private final ProjectTaskStatusComponent highlightWidget;
	private final ProjectMessageListComponent messageWidget;

	public ProjectSummaryViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		this.prjView = new ProjectInformationComponent();
		this.addComponent(this.prjView);

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		this.addComponent(layout);

		final VerticalLayout leftPanel = new VerticalLayout();

		this.activityPanel = new ProjectActivityStreamComponent();
		leftPanel.addComponent(new LazyLoadWrapper(this.activityPanel));
		layout.addComponent(leftPanel);

		final VerticalLayout rightPanel = new VerticalLayout();
		rightPanel.setMargin(false, false, false, true);
		rightPanel.setSpacing(true);
		layout.addComponent(rightPanel);

		this.messageWidget = new ProjectMessageListComponent();
		rightPanel.addComponent(new LazyLoadWrapper(this.messageWidget));

		this.membersWidget = new ProjectMembersWidget();
		this.highlightWidget = new ProjectTaskStatusComponent();
		rightPanel.addComponent(new LazyLoadWrapper(this.membersWidget));
		rightPanel.addComponent(new LazyLoadWrapper(this.highlightWidget));
	}

	@Override
	public void displayDashboard() {
		this.activityPanel.showProjectFeeds();
		this.prjView.displayProjectInformation();
		this.membersWidget.showInformation();
		this.highlightWidget.showProjectTasksByStatus();
		this.messageWidget.showLatestMessages();
	}
}
