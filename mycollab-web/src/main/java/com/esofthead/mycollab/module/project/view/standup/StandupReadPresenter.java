package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupReadPresenter extends AbstractPresenter<StandupReadView> {
	private static final long serialVersionUID = 1L;

	public StandupReadPresenter() {
		super(StandupReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
