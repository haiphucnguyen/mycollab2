package com.esofthead.mycollab.schedule.email;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;

public abstract class GenericLinkGenerator {

	public String generateUserPreviewFullLink(String username) {
		return getSiteUrl() + ProjectLinkUtils.URL_PREFIX_PARAM
				+ "account/user/preview/" + UrlEncodeDecoder.encode(username);
	}

	abstract public String getSiteUrl();
}
