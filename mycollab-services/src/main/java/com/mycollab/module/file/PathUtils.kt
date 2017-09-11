package com.mycollab.module.file

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
object PathUtils {
    fun buildPath(sAccountId: Int?, objectPath: String): String {
        return (if (sAccountId == null) "" else sAccountId.toString() + "/") + objectPath
    }

    fun getProjectLogoPath(accountId: Int?, projectId: Int?): String {
        return String.format("%d/project/%d/.attachments", accountId, projectId)
    }

    fun getEntityLogoPath(accountId: Int?): String {
        return String.format("%d/.assets", accountId)
    }

    fun getProjectDocumentPath(accountId: Int?, projectId: Int?): String {
        return String.format("%d/project/%d/.page", accountId, projectId)
    }

    fun buildLogoPath(sAccountId: Int?, logoFileName: String, logoSize: Int?): String {
        return String.format("%d/.assets/%s_%d.png", sAccountId, logoFileName, logoSize)
    }

    fun buildFavIconPath(sAccountId: Int?, favIconFileName: String): String {
        return String.format("%d/.assets/%s.ico", sAccountId, favIconFileName)
    }
}
