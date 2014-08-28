package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FileDashboardPresenter extends
		AbstractPresenter<FileDashboardView> {
	private static final long serialVersionUID = 1L;

	public FileDashboardPresenter() {
		super(FileDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		FileContainer projectViewContainer = (FileContainer) container;
		projectViewContainer.removeAllComponents();
		projectViewContainer.addComponent(view);

		view.displayProjectFiles();

		ProjectBreadcrumb breadcrumb = ViewManager
				.getCacheComponent(ProjectBreadcrumb.class);
		breadcrumb.gotoFileList();
	}

}
