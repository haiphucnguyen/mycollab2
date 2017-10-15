package com.mycollab.ondemand.module.file.service.impl

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.mycollab.core.MyCollabException
import com.mycollab.module.file.service.RawContentService
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration

import java.io.InputStream

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class S3RawContentServiceImpl : RawContentService {

    private val amazonServiceConfiguration: AmazonServiceConfiguration = AmazonServiceConfiguration.instance

    override fun saveContent(objectPath: String, stream: InputStream) {
        val s3client = AmazonS3ClientBuilder.defaultClient()
        try {
            val metaData = ObjectMetadata()
            metaData.cacheControl = "max-age=8640000"
            metaData.contentLength = stream.available().toLong()
            val request = PutObjectRequest(amazonServiceConfiguration.bucket, objectPath, stream, metaData)

            s3client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: Exception) {
            throw MyCollabException("Can not save s3 object path $objectPath", e)
        }

    }

    override fun getContentStream(objectPath: String): InputStream {
        val s3client = amazonServiceConfiguration.newS3Client()

        try {
            val obj = s3client.getObject(GetObjectRequest(amazonServiceConfiguration.bucket, objectPath))
            return obj.objectContent
        } catch (e: Exception) {
            throw MyCollabException("Can not get s3 resource $objectPath", e)
        }

    }

    override fun removePath(objectPath: String) {
        val s3client = amazonServiceConfiguration.newS3Client()

        try {
            val listObjects = s3client.listObjects(amazonServiceConfiguration.bucket, objectPath)
            for (objectSummary in listObjects.objectSummaries) {
                s3client.deleteObject(amazonServiceConfiguration.bucket, objectSummary.key)
            }
        } catch (e: Exception) {
            throw MyCollabException("Can not remove object path $objectPath", e)
        }

    }

    override fun renamePath(oldPath: String, newPath: String) {
        val s3client = amazonServiceConfiguration.newS3Client()

        try {
            val listObjects = s3client.listObjects(amazonServiceConfiguration.bucket, oldPath)
            for (objectSummary in listObjects.objectSummaries) {
                val key = objectSummary.key
                val appendPath = key.substring(oldPath.length)
                val newAppPath = newPath + appendPath

                val copyRequest = CopyObjectRequest(
                        amazonServiceConfiguration.bucket, key,
                        amazonServiceConfiguration.bucket, newAppPath)
                copyRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead)
                s3client.copyObject(copyRequest)

                val deleteRequest = DeleteObjectRequest(amazonServiceConfiguration.bucket, key)
                s3client.deleteObject(deleteRequest)
            }

        } catch (e: Exception) {
            throw MyCollabException("Can not rename from path $oldPath to $newPath", e)
        }

    }

    override fun movePath(oldPath: String, destinationPath: String) {
        removePath(destinationPath)
        renamePath(oldPath, destinationPath)
    }

    override fun getSize(objectPath: String): Long {
        val s3client = amazonServiceConfiguration.newS3Client()

        try {
            val listObjects = s3client.listObjects(amazonServiceConfiguration.bucket, objectPath)
            return listObjects.objectSummaries.map { it.size }.sum()

        } catch (e: Exception) {
            throw MyCollabException("Can not get size of path $objectPath", e)
        }

    }
}
