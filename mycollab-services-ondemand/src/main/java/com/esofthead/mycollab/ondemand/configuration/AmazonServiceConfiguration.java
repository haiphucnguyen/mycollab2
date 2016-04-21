package com.esofthead.mycollab.ondemand.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.esofthead.mycollab.configuration.ApplicationProperties;

import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class AmazonServiceConfiguration {
    private static final String AWS_KEY = "s3.key";
    private static final String AWS_SECRET_KEY = "s3.secretKey";
    private static final String BUCKET = "s3.bucket";

    private static final AmazonServiceConfiguration instance = new AmazonServiceConfiguration();

    private String awsKey;
    private String awsSecretKey;
    private String bucket;

    private AmazonServiceConfiguration() {
//        Properties props = ApplicationProperties.getAppProperties();
//        awsKey = props.getProperty(AWS_KEY);
//        awsSecretKey = props.getProperty(AWS_SECRET_KEY);
//        bucket = props.getProperty(BUCKET);
//
//        if ("".equals(awsKey) || "".equals(awsSecretKey) || "".equals(bucket)) {
//            throw new IllegalArgumentException(
//                    "Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set");
//        }
    }

    public static final String getBucket() {
        return instance.bucket;
    }

    public static final AWSCredentials amazonCredentials() {
//        return new BasicAWSCredentials(instance.awsKey, instance.awsSecretKey);
        return new BasicAWSCredentials("AKIAJ5BHX5QOTJQ4QUAQ", "PU9HdTqMrwkypWo0eyU2myxLxcMTp55KHhCLNOYU");
    }
}
