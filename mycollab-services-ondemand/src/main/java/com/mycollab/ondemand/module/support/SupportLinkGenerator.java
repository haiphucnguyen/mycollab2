package com.mycollab.ondemand.module.support;

import com.mycollab.common.UrlEncodeDecoder;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class SupportLinkGenerator {
    public static String generateUnsubscribeEmailFullLink(String siteUrl, String email) {
        return siteUrl + "unsubscribe?email=" + UrlEncodeDecoder.encode(email);
    }
}
