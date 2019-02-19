package com.mycollab.ondemand.module.file.service

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.spring.AppContextUtil

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
        val awsKey = s3Configuration.key
        val awsSecretKey = s3Configuration.secretKey

        bucket = s3Configuration.bucket

        if ("" == awsKey || "" == awsSecretKey || "" == bucket) {
            throw IllegalArgumentException(
                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set")
        }
        System.setProperty(SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR, awsKey)
        System.setProperty(SDKGlobalConfiguration.SECRET_KEY_ENV_VAR, awsSecretKey)
    }

    fun newS3Client(): AmazonS3 = AmazonS3ClientBuilder.defaultClient()

    companion object {
        @JvmStatic val instance = AmazonServiceConfiguration()
    }
}
