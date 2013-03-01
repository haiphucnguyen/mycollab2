package com.esofthead.mycollab.module.user.accountsettings.view.parameters;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ProfileScreenData {
	public static class UploadPhoto extends ScreenData<byte[]> {

		public UploadPhoto(byte[] params) {
			super(params);
		}
	}
}
