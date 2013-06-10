package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class FileManagerPresenter extends AbstractPresenter<FileManagerView> {
	private static final long serialVersionUID = 1L;

	public FileManagerPresenter() {
		super(FileManagerView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Files");

		view.displayProjectFiles();

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadcrumb.gotoFileList();
	}

}
