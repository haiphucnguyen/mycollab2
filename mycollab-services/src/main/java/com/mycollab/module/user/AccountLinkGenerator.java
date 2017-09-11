package com.mycollab.module.user;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.common.UrlEncodeDecoder;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class AccountLinkGenerator {

    public static String generateFullProfileLink(String siteUrl) {
        return siteUrl + GenericLinkUtils.INSTANCE.getURL_PREFIX_PARAM() + "account/preview";
    }

    public static String generateRoleLink(Integer userRoleId) {
        return "account/role/preview/" + UrlEncodeDecoder.encode(userRoleId);
    }

    public static String generatePreviewFullRoleLink(String siteUrl, Integer userRoleId) {
        return siteUrl + GenericLinkUtils.INSTANCE.getURL_PREFIX_PARAM() + generateRoleLink(userRoleId);
    }

    public static String generatePreviewFullUserLink(String siteUrl, String username) {
        return siteUrl + GenericLinkUtils.INSTANCE.getURL_PREFIX_PARAM() + "account/user/preview/" + GenericLinkUtils.INSTANCE.encodeParam(username);
    }
}
