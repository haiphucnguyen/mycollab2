package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public class NullViewState extends ViewState{

	public NullViewState() {
		super(null, new EmptyPresenter(), null);
	}
	
	private static class EmptyPresenter implements Presenter {

		@Override
		public void go(ComponentContainer container) {
			
		}

		@Override
		public void go(ComponentContainer container, ScreenData<?> data) {
		}
		
	}

}
