package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BugAdvertisementPresenter extends
		AbstractPresenter<BugAdvertisementView> {
	private static final long serialVersionUID = 1L;

	public BugAdvertisementPresenter() {
		super(BugAdvertisementView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TrackerContainer trackerContainer = (TrackerContainer) container;
		trackerContainer.removeAllComponents();
		trackerContainer.addComponent(view.getWidget());
	}

}
