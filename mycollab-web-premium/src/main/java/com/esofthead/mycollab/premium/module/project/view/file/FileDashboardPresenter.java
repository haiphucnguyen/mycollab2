package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class FileDashboardPresenter extends AbstractPresenter<FileDashboardView> {
	private static final long serialVersionUID = 1L;

	public FileDashboardPresenter() {
		super(FileDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		FileContainer projectViewContainer = (FileContainer) container;
		projectViewContainer.removeAllComponents();
		projectViewContainer.addComponent(cacheableView);

		cacheableView.displayProjectFiles();

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadcrumb.gotoFileList();
	}

}
