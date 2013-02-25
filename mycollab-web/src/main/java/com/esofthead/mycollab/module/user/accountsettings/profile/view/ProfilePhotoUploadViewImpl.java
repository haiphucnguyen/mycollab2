package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.vaadin.mvp.HAbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ProfilePhotoUploadViewImpl extends HAbstractView implements
		ProfilePhotoUploadView {
	private static final long serialVersionUID = 1L;

	public ProfilePhotoUploadViewImpl() {

	}

	@Override
	public void editPhoto() {
		this.removeAllComponents();
		VerticalLayout currentPhotoBox = new VerticalLayout();
		
		this.addComponent(currentPhotoBox);
	}
}
