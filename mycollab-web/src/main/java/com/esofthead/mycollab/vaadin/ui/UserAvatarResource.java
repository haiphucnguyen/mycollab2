package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.file.StorageSetting;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Embedded;

public class UserAvatarResource extends Embedded {
	private static final long serialVersionUID = 1L;

	public UserAvatarResource(String username, int size) {
		super();
		Resource avatarRes = null;
		
		if (StorageSetting.isFileStorage()) {
			String userAvatarPath;
		} else if (StorageSetting.isS3Storage()) {
			
		}
		
		this.setSource(avatarRes);
	}

}
