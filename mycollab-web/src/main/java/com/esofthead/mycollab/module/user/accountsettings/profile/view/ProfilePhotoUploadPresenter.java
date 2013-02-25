package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProfilePhotoUploadPresenter extends
		AbstractPresenter<ProfilePhotoUploadView> {
	private static final long serialVersionUID = 1L;

	public ProfilePhotoUploadPresenter() {
		super(ProfilePhotoUploadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProfileContainer profileContainer = (ProfileContainer) container;
		profileContainer.removeAllComponents();

		profileContainer.addComponent(view.getWidget());
		view.editPhoto();
	}

}
