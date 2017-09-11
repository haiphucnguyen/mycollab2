package com.mycollab.configuration

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
interface IDeploymentMode {
    val isDemandEdition: Boolean

    val isCommunityEdition: Boolean

    val isPremiumEdition: Boolean

    fun getSiteUrl(subDomain: String): String
}
