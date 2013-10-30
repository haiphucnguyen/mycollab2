package com.esofthead.mycollab.module.user;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;

public class UserLinkUtils {
	public static String generateUserRoleLink(Integer userRoleId) {
		return ProjectLinkUtils.URL_PREFIX_PARAM + "account/role/preview/"
				+ UrlEncodeDecoder.encode(userRoleId);
	}
}
