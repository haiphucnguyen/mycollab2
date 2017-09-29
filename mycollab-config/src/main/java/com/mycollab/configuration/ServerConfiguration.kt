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
class ServerConfiguration(val storageSystem: String = STORAGE_FILE, val port: Int? = 8080,
                          val apiUrl: String, val pull_method: String?) {

    constructor(): this("", 8080, "", "")

    fun getApiUrl(path: String): String = "$apiUrl$path"

    val isPush: Boolean
        get() = !"pull".equals(pull_method ?: "", ignoreCase = true)

    companion object {

        @JvmField
        val STORAGE_FILE = "file"

        @JvmField
        val STORAGE_S3 = "s3"
    }
}
