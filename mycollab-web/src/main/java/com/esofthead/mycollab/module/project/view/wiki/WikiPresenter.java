package com.esofthead.mycollab.module.project.view.wiki;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class WikiPresenter extends AbstractPresenter<WikiContainer> {
	private static final long serialVersionUID = 1L;

	public WikiPresenter() {
		super(WikiContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
