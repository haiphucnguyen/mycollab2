package com.mycollab.ondemand.module.file.service

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.configuration.EmailConfiguration
import com.mycollab.spring.AppContextUtil
import org.slf4j.LoggerFactory

import java.util.Properties

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
class AmazonServiceConfiguration {

    var bucket: String? = null
        private set

    @Throws(Exception::class)
    private fun AmazonServiceConfiguration() {
        val s3Configuration = AppContextUtil.getSpringBean(S3Configuration::class.java)
        val emailConfiguration = AppContextUtil.getSpringBean(EmailConfiguration::class.java)
        LOG.info("Email configuration $emailConfiguration")

        val awsKey = s3Configuration.key
        val awsSecretKey = s3Configuration.secretKey

        bucket = s3Configuration.bucket

        if ("" == awsKey || "" == awsSecretKey || "" == bucket) {
            throw IllegalArgumentException(
                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set")
        } else {
            LOG.info("Load s3 configuration successfully with key ${awsKey.substring(3)} secretKey ${awsSecretKey.substring(3)} and bucket $bucket")
        }
        System.setProperty(SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR, awsKey)
        System.setProperty(SDKGlobalConfiguration.SECRET_KEY_ENV_VAR, awsSecretKey)
    }

    fun newS3Client(): AmazonS3 = AmazonS3ClientBuilder.defaultClient()

    companion object {
        val LOG = LoggerFactory.getLogger(AmazonServiceConfiguration::class.java)

        @JvmStatic val instance = AmazonServiceConfiguration()
    }
}
