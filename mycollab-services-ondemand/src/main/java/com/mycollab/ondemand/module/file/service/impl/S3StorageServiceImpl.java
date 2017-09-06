package com.mycollab.ondemand.module.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Amazon S3 Configuration
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class S3StorageServiceImpl extends AbstractStorageService {

    @Autowired
    private ServerConfiguration serverConfiguration;

    public AmazonS3 newS3Client() {
        return AmazonS3ClientBuilder.defaultClient();
    }

    public String getBucket() {
        return AmazonServiceConfiguration.getInstance().getBucket();
    }

    @Override
    public String generateAssetRelativeLink(String resourceId) {
        return serverConfiguration.getCdnUrl() + resourceId;
    }
}
