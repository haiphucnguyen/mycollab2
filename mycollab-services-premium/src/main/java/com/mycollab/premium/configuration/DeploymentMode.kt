package com.mycollab.premium.configuration

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.db.persistence.service.IService
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Component
@Profile("production")
@Order(value = 1)
class DeploymentMode(private val serverConfiguration: ServerConfiguration) : IDeploymentMode, IService {

    override val isDemandEdition: Boolean
        get() = false

    override val isCommunityEdition: Boolean
        get() = false

    override val isPremiumEdition: Boolean
        get() = true

    override fun getSiteUrl(subDomain: String?): String = String.format(serverConfiguration.siteUrl, SiteConfiguration.getServerAddress(), serverConfiguration.port)

    override fun getResourceDownloadUrl(): String = String.format(serverConfiguration.resourceDownloadUrl, SiteConfiguration.getServerAddress(), serverConfiguration.port)

    override fun getCdnUrl(): String = String.format(serverConfiguration.cdnUrl, SiteConfiguration.getServerAddress(), serverConfiguration.port)
}
