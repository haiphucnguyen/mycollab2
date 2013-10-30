package com.esofthead.mycollab.module.user;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.common.UrlEncodeDecoder;

public class UserLinkUtils {
	public static String generateUserRoleLink(Integer userRoleId) {
		return GenericLinkUtils.URL_PREFIX_PARAM + "account/role/preview/"
				+ UrlEncodeDecoder.encode(userRoleId);
	}
}
