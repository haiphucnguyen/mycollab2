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
package com.esofthead.mycollab.configuration;

import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Amazon S3 Configuration
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class S3StorageConfiguration implements StorageConfiguration {

	public static final String AWS_KEY = "s3.key";
	public static final String AWS_SECRET_KEY = "s3.secretKey";
	public static final String BUCKET = "s3.bucket";
	private static final String S3_DOWNLOAD_URL = "s3.downloadurl";

	private String awsKey;
	private String awsSecretKey;
	private String bucket;

	private S3StorageConfiguration(Properties props) {
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
			if (userAvatarId == null || "".equals(userAvatarId.trim())) {
				return S3AssetsResource
						.generateResourceLink("icons/default_user_avatar_"
								+ size + ".png");
			} else {
				return s3UrlPath + "avatar/" + userAvatarId + "_" + size
						+ ".png";
			}
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
