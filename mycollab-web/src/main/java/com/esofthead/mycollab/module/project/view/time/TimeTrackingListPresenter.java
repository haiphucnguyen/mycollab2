package com.esofthead.mycollab.module.project.view.time;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TimeTrackingListPresenter extends AbstractPresenter<TimeTrackingListView> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingListPresenter() {
		super(TimeTrackingListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {

	}
}
