package com.mycollab.ondemand.module.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.mycollab.core.MyCollabException;
import com.mycollab.module.file.service.RawContentService;
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration;

import java.io.InputStream;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class S3RawContentServiceImpl implements RawContentService {

    private AmazonServiceConfiguration amazonServiceConfiguration;

    public S3RawContentServiceImpl() {
        this.amazonServiceConfiguration = AmazonServiceConfiguration.getInstance();
    }

    @Override
    public void saveContent(String objectPath, InputStream stream) {
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
        try {
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setCacheControl("max-age=8640000");
            metaData.setContentLength(stream.available());
            PutObjectRequest request = new PutObjectRequest(amazonServiceConfiguration.getBucket(), objectPath, stream, metaData);

            s3client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new MyCollabException("Can not save s3 object path " + objectPath, e);
        }
    }

    @Override
    public InputStream getContentStream(String objectPath) {
        AmazonS3 s3client = amazonServiceConfiguration.newS3Client();

        try {
            S3Object obj = s3client.getObject(new GetObjectRequest(amazonServiceConfiguration.getBucket(), objectPath));
            return obj.getObjectContent();
        } catch (Exception e) {
            throw new MyCollabException("Can not get s3 resource " + objectPath, e);
        }
    }

    @Override
    public void removePath(String objectPath) {
        AmazonS3 s3client = amazonServiceConfiguration.newS3Client();

        try {
            ObjectListing listObjects = s3client.listObjects(amazonServiceConfiguration.getBucket(), objectPath);
            for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
                s3client.deleteObject(amazonServiceConfiguration.getBucket(), objectSummary.getKey());
            }
        } catch (Exception e) {
            throw new MyCollabException("Can not remove object path " + objectPath, e);
        }
    }

    @Override
    public void renamePath(String oldPath, String newPath) {
        AmazonS3 s3client = amazonServiceConfiguration.newS3Client();

        try {
            ObjectListing listObjects = s3client.listObjects(amazonServiceConfiguration.getBucket(), oldPath);
            for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
                String key = objectSummary.getKey();
                String appendPath = key.substring(oldPath.length());
                String newAppPath = newPath + appendPath;

                CopyObjectRequest copyRequest = new CopyObjectRequest(
                        amazonServiceConfiguration.getBucket(), key,
                        amazonServiceConfiguration.getBucket(), newAppPath);
                copyRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead);
                s3client.copyObject(copyRequest);

                DeleteObjectRequest deleteRequest = new DeleteObjectRequest(amazonServiceConfiguration.getBucket(), key);
                s3client.deleteObject(deleteRequest);
            }

        } catch (Exception e) {
            throw new MyCollabException("Can not rename from path " + oldPath + " to " + newPath, e);
        }

    }

    @Override
    public void movePath(String oldPath, String destinationPath) {
        removePath(destinationPath);
        renamePath(oldPath, destinationPath);
    }

    @Override
    public long getSize(String path) {
        AmazonS3 s3client = amazonServiceConfiguration.newS3Client();

        try {
            ObjectListing listObjects = s3client.listObjects(amazonServiceConfiguration.getBucket(), path);
            long size = 0;
            for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
                size += objectSummary.getSize();
            }

            return size;

        } catch (Exception e) {
            throw new MyCollabException("Can not get size of path " + path, e);
        }
    }
}
