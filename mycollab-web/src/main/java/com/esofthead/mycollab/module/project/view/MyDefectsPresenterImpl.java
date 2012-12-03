package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.view.MyDefectsView.MyDefectsPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class MyDefectsPresenterImpl extends AbstractPresenter implements MyDefectsPresenter{
	
	private static final long serialVersionUID = 1L;
	private MyDefectsView view;
	
	public MyDefectsPresenterImpl(MyDefectsView view) {
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
