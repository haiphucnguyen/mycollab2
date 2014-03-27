/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.tools;

import java.io.File;
import java.io.FileInputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.esofthead.mycollab.core.utils.MimeTypesUtil;

public class SyncWebResourcesToS3Command {

	public static void syncLocalResourcesToS3(String localPath, String s3Path) {
		AWSCredentials myCredentials = new BasicAWSCredentials("key", "secret");
		AmazonS3 s3client = new AmazonS3Client(myCredentials);

		File localFolder = new File(localPath);
		syncFoldersToS3(s3client, localFolder, localFolder.getAbsolutePath(),
				s3Path);
	}

	private static void syncFoldersToS3(AmazonS3 s3client, File file,
			String baseFolderPath, String s3Path) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File subFile : files) {
				try {
					ObjectMetadata metaData = new ObjectMetadata();
					metaData.setCacheControl("max-age=8640000");
					metaData.setContentType(MimeTypesUtil
							.detectMimeType(subFile.getAbsolutePath()));
					metaData.setContentLength(subFile.length());
					String objectPath = s3Path
							+ subFile.getAbsolutePath().substring(
									baseFolderPath.length() + 1);
					PutObjectRequest request = new PutObjectRequest("mycollab",
							objectPath, new FileInputStream(subFile), metaData);

					s3client.putObject(request
							.withCannedAcl(CannedAccessControlList.PublicRead));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void main(String[] args) {
		syncLocalResourcesToS3(
				"/Users/haiphucnguyen/Documents/workspace/mycollab/mycollab-web/src/main/webapp/assets/images/email",
				"assets/images/email/");
	}
}
