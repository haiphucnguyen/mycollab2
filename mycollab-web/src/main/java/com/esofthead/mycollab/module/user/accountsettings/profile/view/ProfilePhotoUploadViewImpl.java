package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class ProfilePhotoUploadViewImpl extends AbstractView implements ProfilePhotoUploadView {
	private static final long serialVersionUID = 1L;

	public ProfilePhotoUploadViewImpl() {
		this.addComponent(new Label("Photo profile"));
	}
}
