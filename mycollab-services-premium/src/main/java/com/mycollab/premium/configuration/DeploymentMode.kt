package com.mycollab.premium.configuration

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.db.persistence.service.IService
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Component
@Order(value = 1)
class DeploymentMode(private val serverConfiguration: ServerConfiguration) : IDeploymentMode, IService {

    override val isDemandEdition: Boolean
        get() = false

    override val isCommunityEdition: Boolean
        get() = false

    override val isPremiumEdition: Boolean
        get() = true

    override fun getSiteUrl(subDomain: String?) = String.format(serverConfiguration.siteUrl, serverConfiguration.address, serverConfiguration.port)

    override fun getResourceDownloadUrl() = String.format(serverConfiguration.resourceDownloadUrl, serverConfiguration.address, serverConfiguration.port)

    override fun getCdnUrl() = String.format(serverConfiguration.cdnUrl, serverConfiguration.address, serverConfiguration.port)
}
