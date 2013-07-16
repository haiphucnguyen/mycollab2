package com.esofthead.mycollab.module.file.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.S3StorageConfig;
import com.esofthead.mycollab.module.file.service.RawContentService;

public class AmazonRawContentServiceImpl implements RawContentService {

	private static final int BUFFER_SIZE = 1024;

	public void saveContent(String objectPath, InputStream stream) {

		AmazonS3 s3client = S3StorageConfig.getS3Client();
		try {
			/*
			 * need to save to temporary buffer
			 */
			String fileExtension = "";
			int startFileExtensionIndex = objectPath.lastIndexOf(".");
			if (startFileExtensionIndex >= 0) {
				fileExtension = objectPath.substring(startFileExtensionIndex);
			}
			File tmpFile = File.createTempFile(
					String.valueOf(System.currentTimeMillis()), fileExtension);
			BufferedOutputStream outStream = new BufferedOutputStream(
					new FileOutputStream(tmpFile));
			byte[] buffer = new byte[BUFFER_SIZE];
			int byteRead = 0;

			while ((byteRead = stream.read(buffer)) >= 0) {
				outStream.write(buffer, 0, byteRead);
			}
			outStream.flush();
			outStream.close();

			stream.close();

			PutObjectRequest request = new PutObjectRequest(
					S3StorageConfig.getBucket(), objectPath, tmpFile);

			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setCacheControl("max-age=8640000");

			request.setMetadata(metaData);

			s3client.putObject(request
					.withCannedAcl(CannedAccessControlList.PublicRead));

			tmpFile.deleteOnExit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public InputStream getContent(String objectPath) {
		AmazonS3 s3client = S3StorageConfig.getS3Client();

		try {
			S3Object obj = s3client.getObject(new GetObjectRequest(
					S3StorageConfig.getBucket(), objectPath));

			return obj.getObjectContent();
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	public void removeContent(String object) {
		AmazonS3 s3client = S3StorageConfig.getS3Client();

		try {
			ObjectListing listObjects = s3client.listObjects(
					S3StorageConfig.getBucket(), object);
			for (S3ObjectSummary objectSummary : listObjects
					.getObjectSummaries()) {
				s3client.deleteObject(S3StorageConfig.getBucket(),
						objectSummary.getKey());
			}

		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public void rename(String oldPath, String newPath) {
		AmazonS3 s3client = S3StorageConfig.getS3Client();

		try {
			ObjectListing listObjects = s3client.listObjects(
					S3StorageConfig.getBucket(), oldPath);
			for (S3ObjectSummary objectSummary : listObjects
					.getObjectSummaries()) {
				String key = objectSummary.getKey();
				String appendPath = key.substring(oldPath.length());
				String newAppPath = newPath + appendPath;

				CopyObjectRequest copyRequest = new CopyObjectRequest(
						S3StorageConfig.getBucket(), key,
						S3StorageConfig.getBucket(), newAppPath);
				copyRequest
						.withCannedAccessControlList(CannedAccessControlList.PublicRead);
				s3client.copyObject(copyRequest);

				DeleteObjectRequest deleteRequest = new DeleteObjectRequest(
						S3StorageConfig.getBucket(), key);
				s3client.deleteObject(deleteRequest);
			}

		} catch (Exception e) {
			throw new MyCollabException(e);
		}

	}

	// public static void mainUpdateCache(String[] args) {
	// AmazonS3 s3client = S3StorageConfig.getS3Client();
	//
	// try {
	// ObjectListing listObjects = s3client.listObjects("mycollab_assets",
	// "icons");
	// for (S3ObjectSummary objectSummary : listObjects
	// .getObjectSummaries()) {
	// System.out.println("Key: " + objectSummary.getKey());
	//
	// ObjectMetadata metaData = new ObjectMetadata();
	// metaData.setCacheControl("max-age=8640000");
	// String key = objectSummary.getKey();
	// String contentType = "";
	// int lastIndex = key.lastIndexOf(".");
	// if (lastIndex >= 0) {
	// String suffix = key.substring(lastIndex + 1);
	//
	// if (suffix.equalsIgnoreCase("jpg")) {
	// contentType = "image/jpg";
	// } else if (suffix.equalsIgnoreCase("png")) {
	// contentType = "image/png";
	// } else if (suffix.equals("zip")) {
	// contentType = "application/zip";
	// }
	//
	// System.out
	// .println("Suffix: " + suffix + "--" + contentType);
	// if (contentType != "") {
	// metaData.setContentType(contentType);
	// }
	// }
	//
	// CopyObjectRequest request = new CopyObjectRequest(
	// "mycollab_assets", objectSummary.getKey(),
	// "mycollab_assets", objectSummary.getKey());
	// request.withNewObjectMetadata(metaData)
	// .withCannedAccessControlList(
	// CannedAccessControlList.PublicRead);
	// CopyObjectResult result = s3client.copyObject(request);
	// System.out.println("Result: " + result + "---");
	// }
	//
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }

	public static void main(String[] args) {
		AmazonRawContentServiceImpl service = new AmazonRawContentServiceImpl();
		service.rename(

		"1/common-comment1", "1/common-comment");
	}

	@Override
	public void moveContent(String olPath, String destinationPath) {
		// TODO Auto-generated method stub
		
	}
}
