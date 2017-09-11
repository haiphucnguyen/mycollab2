package com.mycollab.community.configuration

import com.mycollab.configuration.ApplicationProperties
import com.mycollab.configuration.IDeploymentMode
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.configuration.SiteConfiguration
import org.springframework.beans.factory.annotation.Autowired
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
class DeploymentMode : IDeploymentMode {

    @Autowired
    private val serverConfiguration: ServerConfiguration? = null

    override val isDemandEdition: Boolean
        get() = false

    override val isCommunityEdition: Boolean
        get() = true

    override val isPremiumEdition: Boolean
        get() = false

    override fun getSiteUrl(subDomain: String): String {
        return String.format(ApplicationProperties.getString(ApplicationProperties.APP_URL),
                SiteConfiguration.getServerAddress(), serverConfiguration!!.port)
    }
}
