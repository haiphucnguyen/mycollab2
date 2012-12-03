package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.addon.calendar.ui.Calendar;

public class ActivityCalendarViewImpl extends AbstractView implements
		ActivityCalendarView {
	private static final long serialVersionUID = 1L;
	
	public ActivityCalendarViewImpl() {
		super();
		Calendar calendar = new Calendar();
		this.addComponent(calendar);
	}

}
