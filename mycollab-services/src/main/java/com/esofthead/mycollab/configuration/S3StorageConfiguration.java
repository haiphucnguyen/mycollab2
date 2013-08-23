package com.esofthead.mycollab.configuration;

import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3StorageConfiguration implements StorageConfiguration {

	private static final String AWS_KEY = "s3.key";
	private static final String AWS_SECRET_KEY = "s3.secretKey";
	private static final String BUCKET = "s3.bucket";
	private static final String S3_DOWNLOAD_URL = "s3.downloadurl";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	private S3StorageConfiguration(Properties props) {
		awsKey = ApplicationProperties.getString(AWS_KEY);
		awsSecretKey = ApplicationProperties.getString(AWS_SECRET_KEY);
		bucket = ApplicationProperties.getString(BUCKET);

		if ("".equals(awsKey) || "".equals(awsSecretKey) || "".equals(bucket)) {
			throw new IllegalArgumentException(
					"Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set");
		}
	}

	public final AmazonS3 newS3Client() {
		AWSCredentials myCredentials = new BasicAWSCredentials(awsKey,
				awsSecretKey);
		AmazonS3 s3client = new AmazonS3Client(myCredentials);
		return s3client;
	}

	public static S3StorageConfiguration build(Properties props) {
		return new S3StorageConfiguration(props);
	}

	public String getBucket() {
		return bucket;
	}

	@Override
	public String generateAvatarPath(String userAvatarId, int size) {
		String s3UrlPath = ApplicationProperties.getString(S3_DOWNLOAD_URL, "");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			return s3UrlPath + "avatar/" + userAvatarId + "_" + size + ".png";
		}
	}

	@Override
	public String generateResourcePath(String documentPath) {
		String s3UrlPath = ApplicationProperties.getString(S3_DOWNLOAD_URL, "");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			return s3UrlPath + documentPath;
		}
	}

}
