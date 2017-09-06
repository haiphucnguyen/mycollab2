package com.mycollab.ondemand.module.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mycollab.configuration.ApplicationProperties;

import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public final class AmazonServiceConfiguration {
    private static final String AWS_KEY = "s3.key";
    private static final String AWS_SECRET_KEY = "s3.secretKey";
    private static final String BUCKET = "s3.bucket";

    private String bucket;

    private static final AmazonServiceConfiguration instance = new AmazonServiceConfiguration();

    private void AmazonServiceConfiguration() throws Exception {
        Properties props = ApplicationProperties.getAppProperties();
        String awsKey = props.getProperty(AWS_KEY);
        String awsSecretKey = props.getProperty(AWS_SECRET_KEY);

        bucket = props.getProperty(BUCKET);

        if ("".equals(awsKey) || "".equals(awsSecretKey) || "".equals(bucket)) {
            throw new IllegalArgumentException(
                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set");
        }
        System.setProperty("aws.accessKeyId", awsKey);
        System.setProperty("aws.secretKey", awsSecretKey);
    }

    public static AmazonServiceConfiguration getInstance() {
        return instance;
    }

    public AmazonS3 newS3Client() {
        return AmazonS3ClientBuilder.defaultClient();
    }

    public String getBucket() {
        return bucket;
    }
}
