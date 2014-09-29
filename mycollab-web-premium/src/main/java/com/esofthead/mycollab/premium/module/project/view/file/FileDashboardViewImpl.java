package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.vaadin.shared.ui.MarginInfo;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class FileDashboardViewImpl extends AbstractPageView implements
		FileDashboardView {
	private static final long serialVersionUID = 1L;

	@Override
	public void displayProjectFiles() {
		this.setWidth("100%");
		this.setMargin(new MarginInfo(false, true, false, true));
		final int projectId = CurrentProjectVariables.getProjectId();
		String rootPath = String.format("%d/project/%d",
				AppContext.getAccountId(), projectId);

		FileDashboardComponent dashboardComponent = new FileDashboardComponent(
				rootPath) {
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

		dashboardComponent.displayResources();
	}

}
