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
    var version: String = "0.0"
    var communityDownloadLink: String = ""
    var altCommunityDownloadLink: String = ""
    var communityUpgradeLink: String = ""
    var premiumDownloadLink: String = ""
    var premiumUpgradeLink: String = ""
}
