package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.module.crm.events.DocumentEvent;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent;
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
						new DocumentEvent.Search(FileDashboardViewImpl.this,
								searchCriteria));
			}

		};
		dashboardComponent.setWidth("100%");
		this.addComponent(dashboardComponent);
	}

	@Override
	public void displayFiles() {
		String rootPath = String.format("%d/.crm", AppContext.getAccountId());
		dashboardComponent.displayResources(rootPath, "Documents");
	}
}
