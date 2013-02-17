package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class SendEmailVerificationSuccessPresenter extends
		AbstractPresenter<SendEmailVerificationSuccessView> {
	private static final long serialVersionUID = 1L;

	public SendEmailVerificationSuccessPresenter() {
		super(SendEmailVerificationSuccessView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
