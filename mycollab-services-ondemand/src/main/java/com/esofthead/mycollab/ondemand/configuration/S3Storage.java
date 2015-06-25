/**
 * This file is part of mycollab-config.
 *
 * mycollab-config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-config.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.ondemand.configuration;

import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.Storage;

/**
 * Amazon S3 Configuration
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class S3Storage extends Storage {
	private static final String AWS_KEY = "s3.key";
	private static final String AWS_SECRET_KEY = "s3.secretKey";
	private static final String BUCKET = "s3.bucket";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	public S3Storage() {
		Properties props = ApplicationProperties.getAppProperties();
		awsKey = props.getProperty(AWS_KEY);
		awsSecretKey = props.getProperty(AWS_SECRET_KEY);
		bucket = props.getProperty(BUCKET);

		if ("".equals(awsKey) || "".equals(awsSecretKey) || "".equals(bucket)) {
			throw new IllegalArgumentException(
					"Invalid s3 configuration. All values awsKey, awsSecretKey, bucket must be set");
		}
	}

	public final AmazonS3 newS3Client() {
		AWSCredentials myCredentials = new BasicAWSCredentials(awsKey, awsSecretKey);
		return new AmazonS3Client(myCredentials);
	}

	public String getBucket() {
		return bucket;
	}
}
