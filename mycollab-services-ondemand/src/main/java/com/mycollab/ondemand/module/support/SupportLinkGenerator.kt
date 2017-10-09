package com.mycollab.ondemand.module.support

import com.mycollab.common.UrlEncodeDecoder

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
object SupportLinkGenerator {
    @JvmStatic
    fun generateUnsubscribeEmailFullLink(siteUrl: String, email: String): String =
            "${siteUrl}unsubscribe?email=${UrlEncodeDecoder.encode(email)}"
}
