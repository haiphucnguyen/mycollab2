package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.user.MyFeedsView.MyFeedsPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class MyFeedsPresenterImpl extends AbstractPresenter implements MyFeedsPresenter{

	private MyFeedsView view;
	
	public MyFeedsPresenterImpl(MyFeedsView view) {
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
