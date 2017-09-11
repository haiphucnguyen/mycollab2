package com.mycollab.ondemand.configuration

import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.configuration.SiteConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value = 1)
@Component
@Profile("production")
class DeploymentMode : IDeploymentMode {

    @Autowired
    private val serverConfiguration: ServerConfiguration? = null

    override val isDemandEdition: Boolean
        get() = true

    override val isCommunityEdition: Boolean
        get() = false

    override val isPremiumEdition: Boolean
        get() = false

    override fun getSiteUrl(subDomain: String): String {
        return String.format(serverConfiguration!!.appUrl, subDomain)
    }
}
