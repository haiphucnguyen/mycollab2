package com.mycollab.ondemand.module.file.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.ondemand.configuration.DeploymentMode
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

/**
 * Amazon S3 Configuration
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
class S3StorageServiceImpl : AbstractStorageService() {

    val bucket: String?
        get() = AmazonServiceConfiguration.instance.bucket

    fun newS3Client(): AmazonS3 = AmazonS3ClientBuilder.defaultClient()

    override fun generateAssetRelativeLink(resourceId: String): String = """${deploymentMode.getCdnUrl()}$resourceId"""
}
