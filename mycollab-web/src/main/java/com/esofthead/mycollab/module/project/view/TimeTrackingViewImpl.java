package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class TimeTrackingViewImpl extends AbstractView implements
		TimeTrackingView {
	private static final long serialVersionUID = 1L;

	public TimeTrackingViewImpl() {
		this.addComponent(new Label("Time Tracking View"));
	}

	@Override
	public void display() {

	}

}
