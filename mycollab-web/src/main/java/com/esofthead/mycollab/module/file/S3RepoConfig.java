package com.esofthead.mycollab.module.file;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.esofthead.mycollab.common.ApplicationProperties;

public class S3RepoConfig {
	private static final String AWS_KEY = "s3.key";
	private static final String AWS_SECRET_KEY = "s3.secretKey";
	private static final String BUCKET = "s3.bucket";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	private static S3RepoConfig instance;

	private S3RepoConfig(String awsKey, String awsSecretKey, String bucket) {
		this.awsKey = awsKey;
		this.awsSecretKey = awsSecretKey;
		this.bucket = bucket;
	}

	static {
		try {
			String awsKey = ApplicationProperties.getProperty(AWS_KEY);
			String awsSecretKey = ApplicationProperties
					.getProperty(AWS_SECRET_KEY);
			String bucket = ApplicationProperties.getProperty(BUCKET);

			instance = new S3RepoConfig(awsKey, awsSecretKey, bucket);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getBucket() {
		return instance.bucket;
	}

	public static final AmazonS3 getS3Client() {
		AWSCredentials myCredentials = new BasicAWSCredentials(instance.awsKey,
				instance.awsSecretKey);
		AmazonS3 s3client = new AmazonS3Client(myCredentials);
		return s3client;
	}
}
