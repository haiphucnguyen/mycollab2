package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;

public class AccountLinkGenerator {
	public static String generateUserPreviewLink(String username) {
		return "account/user/preview/" + UrlEncodeDecoder.encode(username);
	}

	public static String generateUserPreviewFullLink(String username) {
		if (username == null || username.trim().equals("")) {
			return "";
		}

		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ "?url=" + generateUserPreviewLink(username);
	}
}
