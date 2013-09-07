package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;

@ViewComponent
public class FileDashboardViewImpl extends AbstractView implements
		FileDashboardView {
	private static final long serialVersionUID = 1L;

	private FileDashboardComponent dashboardComponent;

	public FileDashboardViewImpl() {
		this.setWidth("100%");
		dashboardComponent = new FileDashboardComponent() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void doSearch(FileSearchCriteria searchCriteria) {
				EventBus.getInstance().fireEvent(
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
