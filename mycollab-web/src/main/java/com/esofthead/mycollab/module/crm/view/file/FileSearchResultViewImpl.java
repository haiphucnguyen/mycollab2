package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileSearchResultComponent;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class FileSearchResultViewImpl extends AbstractView implements
		FileSearchResultView {
	private static final long serialVersionUID = 1L;

	private FileSearchResultComponent searchResultComp;

	public FileSearchResultViewImpl() {
		searchResultComp = new FileSearchResultComponent() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void backView() {
				EventBus.getInstance().fireEvent(
						new ProjectContentEvent.GotoDashboard(
								FileSearchResultViewImpl.this));

			}
		};
		this.addComponent(searchResultComp);
	}

	@Override
	public void displaySearchResult(FileSearchCriteria searchCriteria) {
		searchResultComp.displaySearchResult(searchCriteria.getRootFolder(),
				searchCriteria.getBaseFolder(), searchCriteria.getFileName());
	}

}
