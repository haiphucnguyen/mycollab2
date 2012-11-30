package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.view.MyTasksView.MyTasksPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class MyTasksPresenterImpl extends AbstractPresenter implements MyTasksPresenter{
	private MyTasksView view;
	
	public MyTasksPresenterImpl(MyTasksView view) {
		this.view = view;
	}

	@Override
	public void doDefaultSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		// TODO Auto-generated method stub
		
	}

}
