package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

public class ProjectViewPresenter extends AbstractPresenter<ProjectView> {
	private static final long serialVersionUID = 1L;

	public ProjectViewPresenter() {
		super(ProjectView.class);
	}

	@Override
	public void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectContainer prjContainer = (ProjectContainer) container;
		prjContainer.removeAllComponents();
		prjContainer.addComponent((Component) view);
		prjContainer.setComponentAlignment((Component) view,
				Alignment.TOP_CENTER);
		view.displayProject((SimpleProject) data.getParams());
	}

}
