package com.mycollab.ondemand.configuration

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value = 1)
@Component
class DeploymentMode(private val serverConfiguration: ServerConfiguration) : IDeploymentMode {

    override val isDemandEdition: Boolean
        get() = false

    override val isCommunityEdition: Boolean
        get() = false

    override val isPremiumEdition: Boolean
        get() = true

    override fun getSiteUrl(subDomain: String?) = String.format(serverConfiguration.siteUrl, subDomain)

    override fun getResourceDownloadUrl() = serverConfiguration.resourceDownloadUrl

    override fun getCdnUrl() = serverConfiguration.cdnUrl
}
