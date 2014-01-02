package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
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
