package com.esofthead.mycollab.module.file;

import java.util.Properties;

public class S3RepoConfig {
	private static final String CONFIG_FILE = "config.properties";
	private static final String AWS_KEY = "AWS_KEY";
	private static final String AWS_SECRET_KEY = "AWS_SECRET_KEY";
	private static final String BUCKET = "BUCKET";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	private S3RepoConfig(String __awsKey, String __awsSecretKey, String __bucket) {
		this.awsKey = __awsKey;
		this.awsSecretKey = __awsSecretKey;
		this.bucket = __bucket;
	}

	public String getAwsKey() {
		return awsKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public static final S3RepoConfig loadConfig() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_FILE));
			String __awsKey = properties.getProperty(AWS_KEY);
			String __awsSecretKey = properties.getProperty(AWS_SECRET_KEY);
			String __bucket = properties.getProperty(BUCKET);

			return new S3RepoConfig(__awsKey, __awsSecretKey, __bucket);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
