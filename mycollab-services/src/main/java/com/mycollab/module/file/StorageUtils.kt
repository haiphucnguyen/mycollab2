package com.mycollab.module.file

import com.mycollab.core.utils.StringUtils
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.spring.AppContextUtil

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
object StorageUtils {
    fun getResourcePath(documentPath: String): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).getResourcePath(documentPath)
    }

    fun getLogoPath(accountId: Int?, logoName: String, size: Int?): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).getLogoPath(accountId, logoName, size)
    }

    fun getEntityLogoPath(accountId: Int?, id: String, size: Int?): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).getEntityLogoPath(accountId, id, size)
    }

    fun getFavIconPath(sAccountId: Int?, favIconName: String): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).getFavIconPath(sAccountId, favIconName)
    }

    fun getAvatarPath(userAvatarId: String, size: Int?): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).getAvatarPath(userAvatarId, size)
    }

    fun generateAssetRelativeLink(resourceId: String): String {
        return AppContextUtil.getSpringBean(AbstractStorageService::class.java).generateAssetRelativeLink(resourceId)
    }
}
