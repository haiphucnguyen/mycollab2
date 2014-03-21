package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileSearchResultComponent;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.shared.ui.MarginInfo;

@ViewComponent
public class FileSearchResultViewImpl extends AbstractPageView implements
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
		this.setMargin(true);
	}

	@Override
	public void displaySearchResult(FileSearchCriteria searchCriteria) {
		searchResultComp.displaySearchResult(searchCriteria.getRootFolder(),
				searchCriteria.getBaseFolder(), searchCriteria.getFileName());
	}

}
