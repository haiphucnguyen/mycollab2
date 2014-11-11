package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.file.IFileContainer;
import com.esofthead.mycollab.module.project.view.file.IFilePresenter;
import com.esofthead.mycollab.module.project.view.parameters.FileScreenData;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FilePresenter extends AbstractPresenter<IFileContainer> implements
		IFilePresenter {
	private static final long serialVersionUID = 1L;

	public FilePresenter() {
		super(IFileContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("file");

		view.removeAllComponents();
		AbstractPresenter<?> presenter = null;

		if (data instanceof FileScreenData.GotoDashboard) {
			presenter = PresenterResolver
					.getPresenter(FileDashboardPresenter.class);
		} else {
			throw new MyCollabException("No support screen data " + data);
		}

		presenter.go(view, data);
	}

}
