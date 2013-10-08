package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.vaadin.ui.ComponentContainer;

public interface View extends ComponentContainer {

	ComponentContainer getWidget();

	void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener);
}
