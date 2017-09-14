package com.mycollab.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@Profile("production", "test")
@ConfigurationProperties(prefix = "app")
class ApplicationConfiguration {

    var description: String? = null

    var facebookUrl: String? = null

    var twitterUrl: String? = null

    var googleUrl: String? = null

    var linkedinUrl: String? = null

    fun defaultUrls(): Map<String, String> =
            mutableMapOf<String, String>("facebook_url" to (facebookUrl ?: ""),
                    "google_url" to (googleUrl ?: ""),
                    "linkedin_url" to (linkedinUrl ?: ""),
                    "twitter_url" to (twitterUrl ?: ""))
}
