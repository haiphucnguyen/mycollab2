package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupPresenter extends AbstractPresenter<StandupContainer> {
	private static final long serialVersionUID = 1L;

	public StandupPresenter() {
		super(StandupContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
