package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

public class TimeTrackingPresenter extends AbstractPresenter<TimeTrackingView> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingPresenter() {
		super(TimeTrackingView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectModule prjContainer = (ProjectModule) container;
		prjContainer.removeAllComponents();
		prjContainer.addComponent((Component) view);
		prjContainer.setComponentAlignment(view, Alignment.TOP_CENTER);
		view.display();
	}
}
