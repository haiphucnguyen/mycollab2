package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TabSheet.Tab;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.2
 * 
 */
public class AbstractTabPageView extends TabBarView implements PageView {
	private static final long serialVersionUID = 664039475002291943L;

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	/*
	 * Now we use EventBus to manage events
	 * 
	 * @see com.esofthead.mycollab.eventmanager.EventBus
	 */
	@Deprecated
	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
		// Do nothing
	}

	@Override
	public Tab addTab(Component tabContent, String caption, Resource icon) {
		Tab newTab = super.addTab(tabContent, caption, icon);
		((Button) newTab).setHtmlContentAllowed(true);
		return newTab;
	}

}
