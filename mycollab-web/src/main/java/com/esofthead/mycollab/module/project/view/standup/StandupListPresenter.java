package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupListPresenter extends AbstractPresenter<StandupListView> {
	private static final long serialVersionUID = 1L;

	public StandupListPresenter() {
		super(StandupListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
