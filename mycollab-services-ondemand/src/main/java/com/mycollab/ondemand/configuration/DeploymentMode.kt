package com.mycollab.ondemand.configuration

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value = 1)
@Component
@Profile("production")
class DeploymentMode(private val serverConfiguration: ServerConfiguration) : IDeploymentMode {

    override val isDemandEdition: Boolean
        get() = true

    override val isCommunityEdition: Boolean
        get() = false

    override val isPremiumEdition: Boolean
        get() = false

    override fun getSiteUrl(subDomain: String): String = "serverConfiguration.appUrl, subDomain"

    override fun getResourceDownloadUrl(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCdnUrl(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
