package com.mycollab.module.user

import com.mycollab.common.GenericLinkUtils
import com.mycollab.common.UrlEncodeDecoder

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object AccountLinkGenerator {

    @JvmStatic fun generateFullProfileLink(siteUrl: String): String {
        return siteUrl + GenericLinkUtils.URL_PREFIX_PARAM + "account/preview"
    }

    @JvmStatic fun generateRoleLink(userRoleId: Int?): String {
        return "account/role/preview/" + UrlEncodeDecoder.encode(userRoleId)
    }

    @JvmStatic fun generatePreviewFullRoleLink(siteUrl: String, userRoleId: Int?): String {
        return siteUrl + GenericLinkUtils.URL_PREFIX_PARAM + generateRoleLink(userRoleId)
    }

    @JvmStatic fun generatePreviewFullUserLink(siteUrl: String, username: String): String {
        return siteUrl + GenericLinkUtils.URL_PREFIX_PARAM + "account/user/preview/" + GenericLinkUtils.encodeParam(username)
    }
}
