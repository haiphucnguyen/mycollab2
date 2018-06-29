package com.mycollab.ondemand.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Component
@ConfigurationProperties(prefix = "app")
class EditionInfo {
    var version: String? = null
    var communityDownloadLink: String? = null
    var altCommunityDownloadLink: String? = null
    var communityUpgradeLink: String? = null
    var premiumDownloadLink: String? = null
    var premiumUpgradeLink: String? = null
}
