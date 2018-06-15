package com.mycollab.ondemand.module.file.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.configuration.ApplicationProperties

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
        val props = ApplicationProperties.getAppProperties()
        val awsKey = props.getProperty(AWS_KEY)
        val awsSecretKey = props.getProperty(AWS_SECRET_KEY)

        bucket = props.getProperty(BUCKET)

        if ("" == awsKey || "" == awsSecretKey || "" == bucket) {
            throw IllegalArgumentException(
                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set")
        }
        System.setProperty("aws.accessKeyId", awsKey)
        System.setProperty("aws.secretKey", awsSecretKey)
    }

    fun newS3Client(): AmazonS3 {
        return AmazonS3ClientBuilder.defaultClient()
    }

    companion object {
        private const val AWS_KEY = "s3.key"
        private const val AWS_SECRET_KEY = "s3.secretKey"
        private const val BUCKET = "s3.bucket"

        @JvmStatic val instance = AmazonServiceConfiguration()
    }
}
