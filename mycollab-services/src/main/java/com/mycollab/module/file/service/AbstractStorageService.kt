package com.mycollab.module.file.service

import com.mycollab.configuration.ServerConfiguration
import com.mycollab.core.utils.StringUtils
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
abstract class AbstractStorageService {

    @Autowired
    protected val serverConfiguration: ServerConfiguration? = null

    fun getResourcePath(documentPath: String): String =
            serverConfiguration!!.resourceDownloadUrl + documentPath

    fun getLogoPath(accountId: Int, logoName: String?, size: Int): String =
            when {
                StringUtils.isBlank(logoName) -> generateAssetRelativeLink("icons/logo.png")
                else -> "${serverConfiguration!!.resourceDownloadUrl}$accountId/.assets/${logoName}_$size.png"
            }

    fun getEntityLogoPath(accountId: Int, id: String, size: Int): String =
            "${serverConfiguration!!.resourceDownloadUrl}$accountId/.assets/${id}_$size.png"

    fun getFavIconPath(sAccountId: Int, favIconName: String?): String =
            when {
                StringUtils.isBlank(favIconName) -> generateAssetRelativeLink("favicon.ico")
                else -> "${serverConfiguration!!.resourceDownloadUrl}$sAccountId/.assets/$favIconName.ico"
            }

    fun getAvatarPath(userAvatarId: String?, size: Int): String =
            when {
                StringUtils.isBlank(userAvatarId) -> generateAssetRelativeLink(String.format("icons/default_user_avatar_%d.png", size))
                else -> "${serverConfiguration!!.resourceDownloadUrl}avatar/${userAvatarId}_$size.png"
            }

    abstract fun generateAssetRelativeLink(resourceId: String): String
}
