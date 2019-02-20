package com.mycollab.ondemand.module.file.service

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.spring.AppContextUtil
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
class AmazonServiceConfiguration @Throws(Exception::class) private constructor() {

    var bucket: String? = null
        private set

    init {
        val s3Configuration = AppContextUtil.getSpringBean(S3Configuration::class.java)
        val awsKey = s3Configuration.key
        val awsSecretKey = s3Configuration.secretKey
        bucket = s3Configuration.bucket
        if ("" == awsKey || "" == awsSecretKey || "" == bucket) {
            throw IllegalArgumentException(
                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set")
        }
        System.setProperty(SDKGlobalConfiguration.ACCESS_KEY_SYSTEM_PROPERTY, awsKey)
        System.setProperty(SDKGlobalConfiguration.SECRET_KEY_SYSTEM_PROPERTY, awsSecretKey)
    }

    fun newS3Client(): AmazonS3 = AmazonS3ClientBuilder.defaultClient()

    companion object {
        val LOG = LoggerFactory.getLogger(AmazonServiceConfiguration::class.java)

        @JvmStatic
        val instance = AmazonServiceConfiguration()
    }
}
