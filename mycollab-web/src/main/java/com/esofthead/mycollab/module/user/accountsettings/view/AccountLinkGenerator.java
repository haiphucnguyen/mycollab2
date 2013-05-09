package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.web.AppContext;

public class AccountLinkGenerator {
	public static String generateUserPreviewLink(String username) {
		return "account/user/preview/" + UrlEncodeDecoder.encode(username);
	}

	public static String generateUserPreviewFullLink(String username) {
		if (username == null || username.trim().equals("")) {
			return "";
		}

		return "?url="
				+ generateUserPreviewLink(username);
		//TODO: fix for provide the correct account
		// return AppContext.getSiteUrl() + "?url="
		// + generateUserPreviewLink(username);
	}
}
