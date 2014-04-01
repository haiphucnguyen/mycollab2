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
		AWSCredentials myCredentials = new BasicAWSCredentials(
				"AKIAJ5BHX5QOTJQ4QUAQ",
				"PU9HdTqMrwkypWo0eyU2myxLxcMTp55KHhCLNOYU");
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
				if (subFile.isDirectory()) {
					syncFoldersToS3(s3client, subFile, baseFolderPath, s3Path);
				} else {
					try {
						ObjectMetadata metaData = new ObjectMetadata();
						metaData.setCacheControl("max-age=8640000");
						metaData.setContentType(MimeTypesUtil
								.detectMimeType(subFile.getAbsolutePath()));
						metaData.setContentLength(subFile.length());
						String objectPath = s3Path
								+ subFile.getAbsolutePath().substring(
										baseFolderPath.length() + 1);
						System.out.println("Upload file: "
								+ subFile.getAbsolutePath() + " to s3 path: "
								+ objectPath);
						PutObjectRequest request = new PutObjectRequest(
								"mycollab_assets", objectPath,
								new FileInputStream(subFile), metaData);

						s3client.putObject(request
								.withCannedAcl(CannedAccessControlList.PublicRead));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		syncLocalResourcesToS3(
				"/Users/haiphucnguyen/Documents/workspace/mycollab/mycollab-web/src/main/resources/assets",
				"assets/");

		syncLocalResourcesToS3(
				"/Users/haiphucnguyen/Documents/workspace/mycollab/mycollab-web/src/main/resources/VAADIN/themes/mycollab",
				"");
	}
}
