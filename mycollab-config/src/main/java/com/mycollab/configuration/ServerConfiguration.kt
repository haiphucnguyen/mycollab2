package com.mycollab.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@Profile("production")
@ConfigurationProperties(prefix = "server")
class ServerConfiguration {

    var storageSystem = STORAGE_FILE

    var port: Int? = 8080

    var apiUrl: String? = null

    var pull_method: String? = null

    fun getApiUrl(path: String): String {
        return String.format("%s%s", apiUrl, path)
    }

    val resourceDownloadUrl: String
        get() = String.format("http://%s:%d/file/", SiteConfiguration.getServerAddress(), port)

    val cdnUrl: String
        get() = String.format("http://%s:%d/assets/", SiteConfiguration.getServerAddress(), port)

    val appUrl: String
        get() = String.format("http://%s:%d/", SiteConfiguration.getServerAddress(), port)

    val isPush: Boolean
        get() = !"pull".equals(pull_method ?: "", ignoreCase = true)

    companion object {

        @JvmField
        val STORAGE_FILE = "file"

        @JvmField
        val STORAGE_S3 = "s3"
    }
}
