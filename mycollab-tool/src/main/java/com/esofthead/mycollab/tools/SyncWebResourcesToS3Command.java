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

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class SyncWebResourcesToS3Command {

	public static void syncLocalResourcesToS3(String localPath, String s3Path) {
		AWSCredentials myCredentials = new BasicAWSCredentials("key", "secret");
		AmazonS3 s3client = new AmazonS3Client(myCredentials);

		File localFolder = new File(localPath);
		if (!localFolder.isDirectory()) {
			throw new RuntimeException(localPath + " must point to a folder");
		}
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
