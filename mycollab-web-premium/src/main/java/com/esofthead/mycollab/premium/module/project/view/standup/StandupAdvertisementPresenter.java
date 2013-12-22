package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupAdvertisementPresenter extends
		AbstractPresenter<StandupAdvertisementView> {
	private static final long serialVersionUID = 1L;

	public StandupAdvertisementPresenter() {
		super(StandupAdvertisementView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		StandupContainer standupContainer = (StandupContainer) container;
		standupContainer.removeAllComponents();
		standupContainer.addComponent(view.getWidget());
	}
}
