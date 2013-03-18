package com.esofthead.mycollab.module.file;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.web.AppContext;

public class S3StorageConfig {
	private static final String AWS_KEY = "s3.key";
	private static final String AWS_SECRET_KEY = "s3.secretKey";
	private static final String BUCKET = "s3.bucket";
	private static final String S3_DOWNLOAD_URL = "s3.downloadurl";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	private static S3StorageConfig instance;

	private S3StorageConfig(String awsKey, String awsSecretKey, String bucket) {
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

			instance = new S3StorageConfig(awsKey, awsSecretKey, bucket);
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

	public static String getAvatarLink(String username, int size) {
		String s3UrlPath = ApplicationProperties.getProperty(S3_DOWNLOAD_URL,
				"");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			return s3UrlPath + "/" + AppContext.getAccountId() + "/avatar/"
					+ username + "_" + size + ".png";
		}
	}

	public static String getResourceLink(String documentPath) {
		String s3UrlPath = ApplicationProperties.getProperty(S3_DOWNLOAD_URL,
				"");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			return s3UrlPath + "/" + AppContext.getAccountId() + "/"
					+ documentPath;
		}
	}
}
