package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.shared.ui.MarginInfo;

@ViewComponent
public class FileDashboardViewImpl extends AbstractPageView implements
FileDashboardView {
	private static final long serialVersionUID = 1L;

	private FileDashboardComponent dashboardComponent;

	public FileDashboardViewImpl() {
		this.setWidth("100%");
		this.setMargin(new MarginInfo(false, true, false, true));
		dashboardComponent = new FileDashboardComponent() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void doSearch(FileSearchCriteria searchCriteria) {
				EventBusFactory.getInstance().post(
						new ProjectContentEvent.Search(
								FileDashboardViewImpl.this, searchCriteria));

			}

		};
		dashboardComponent.setWidth("100%");
		this.addComponent(dashboardComponent);
	}

	@Override
	public void displayProjectFiles() {
		final int projectId = CurrentProjectVariables.getProjectId();
		String rootPath = String.format("%d/project/%d",
				AppContext.getAccountId(), projectId);
		String rootName = CurrentProjectVariables.getProject().getName();
		dashboardComponent.displayResources(rootPath, rootName);
	}

}
