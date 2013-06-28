package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class FileSearchResultPresenter extends
		AbstractPresenter<FileSearchResultView> {

	private static final long serialVersionUID = 1L;

	public FileSearchResultPresenter() {
		super(FileSearchResultView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Files");

		String[] params = (String[]) data.getParams();
		view.displaySearchResult(params[0], params[1]);
	}

}
