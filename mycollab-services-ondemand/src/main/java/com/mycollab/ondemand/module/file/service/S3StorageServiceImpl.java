package com.mycollab.ondemand.module.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.ondemand.configuration.AmazonServiceConfiguration;
import org.springframework.stereotype.Service;

/**
 * Amazon S3 Configuration
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class S3StorageServiceImpl extends AbstractStorageService {

    public final AmazonS3 newS3Client() {
        return new AmazonS3Client(AmazonServiceConfiguration.amazonCredentials());
    }

    public String getBucket() {
        return AmazonServiceConfiguration.getBucket();
    }

    @Override
    public String generateAssetRelativeLink(String resourceId) {
        return SiteConfiguration.getCdnUrl() + resourceId;
    }
}
