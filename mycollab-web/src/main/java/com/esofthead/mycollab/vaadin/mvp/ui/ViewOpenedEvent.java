package com.esofthead.mycollab.vaadin.mvp.ui;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class ViewOpenedEvent extends ApplicationEvent {

	
	public ViewOpenedEvent(View view) {
		super(view);
	}

	public View getView() {
		return (View) source;
	}

}
