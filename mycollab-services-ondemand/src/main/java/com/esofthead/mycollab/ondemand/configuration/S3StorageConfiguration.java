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
import com.esofthead.mycollab.configuration.MyCollabAssets;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * Amazon S3 Configuration
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class S3StorageConfiguration implements StorageConfiguration {
	private static final String AWS_KEY = "s3.key";
	private static final String AWS_SECRET_KEY = "s3.secretKey";
	private static final String BUCKET = "s3.bucket";
	private static final String S3_DOWNLOAD_URL = "s3.downloadurl";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	public S3StorageConfiguration() {
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
		AWSCredentials myCredentials = new BasicAWSCredentials(awsKey,
				awsSecretKey);
		return new AmazonS3Client(myCredentials);
	}

	public String getBucket() {
		return bucket;
	}

	@Override
	public String getAvatarPath(String userAvatarId, int size) {
		String s3UrlPath = ApplicationProperties.getString(S3_DOWNLOAD_URL, "");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			if (StringUtils.isBlank(userAvatarId)) {
				return MyCollabAssets
						.newResourceLink(String.format("icons/default_user_avatar_%d.png", size));
			} else {
				return String.format("%savatar/%s_%d.png", s3UrlPath, userAvatarId, size);
			}
		}
	}

	@Override
	public String getLogoPath(String accountLogoId, int size) {
		String s3UrlPath = ApplicationProperties.getString(S3_DOWNLOAD_URL, "");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			if (StringUtils.isBlank(accountLogoId)) {
				return MyCollabAssets.newResourceLink("icons/logo.png");
			} else {
				return String.format("%savatar/%s_%d.png", s3UrlPath, accountLogoId, size);
			}
		}
	}

	@Override
	public String getResourcePath(String documentPath) {
		String s3UrlPath = ApplicationProperties.getString(S3_DOWNLOAD_URL, "");
		if ("".equals(s3UrlPath)) {
			return "";
		} else {
			return s3UrlPath + documentPath;
		}
	}

}
