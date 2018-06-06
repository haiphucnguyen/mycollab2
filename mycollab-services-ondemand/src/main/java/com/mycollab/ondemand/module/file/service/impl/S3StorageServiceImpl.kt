package com.mycollab.ondemand.module.file.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration
import org.springframework.stereotype.Service

/**
 * Amazon S3 Configuration
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
open class S3StorageServiceImpl : AbstractStorageService() {

    open val bucket: String?
        get() = AmazonServiceConfiguration.instance.bucket

    open fun newS3Client(): AmazonS3 = AmazonS3ClientBuilder.defaultClient()

    override fun generateAssetRelativeLink(resourceId: String): String = "${deploymentMode.getCdnUrl()}$resourceId"
}
