package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.vaadin.mvp.View;

public interface ProfilePhotoUploadView extends View {
	void editPhoto(byte[] imageData);
}
