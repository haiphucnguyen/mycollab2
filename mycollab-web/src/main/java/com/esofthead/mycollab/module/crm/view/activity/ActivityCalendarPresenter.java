package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ActivityCalendarPresenter implements Presenter{
	private static final long serialVersionUID = 1L;
	
	private ActivityCalendarView view;
	
	public ActivityCalendarPresenter(ActivityCalendarView view) {
		this.view = view;
	}
	
	@Override
	public void go(ComponentContainer container) {
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		// TODO Auto-generated method stub
		
	}

}
