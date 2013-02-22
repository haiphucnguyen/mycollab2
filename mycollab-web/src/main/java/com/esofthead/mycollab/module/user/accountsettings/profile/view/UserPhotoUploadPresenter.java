package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class UserPhotoUploadPresenter extends
		AbstractPresenter<UserPhotoUploadView> {
	private static final long serialVersionUID = 1L;

	public UserPhotoUploadPresenter() {
		super(UserPhotoUploadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
	}

}
