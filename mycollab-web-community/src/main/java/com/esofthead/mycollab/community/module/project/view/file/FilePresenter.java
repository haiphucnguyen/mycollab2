package com.esofthead.mycollab.community.module.project.view.file;

import com.esofthead.mycollab.module.project.file.IFileContainer;
import com.esofthead.mycollab.module.project.file.IFilePresenter;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class FilePresenter extends AbstractPresenter<IFileContainer> implements
		IFilePresenter {
	private static final long serialVersionUID = 1L;

	public FilePresenter() {
		super(IFileContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Files");
	}

}
