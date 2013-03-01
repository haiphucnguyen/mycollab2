package com.esofthead.mycollab.module.file.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.S3StorageConfig;
import com.esofthead.mycollab.module.file.service.RawContentService;

public class AmazonService implements RawContentService {

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
			S3Object obj = s3client.getObject(new GetObjectRequest(S3StorageConfig
					.getBucket(), objectPath));

			return obj.getObjectContent();
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	public void removeContent(String object) {
		AmazonS3 s3client = S3StorageConfig.getS3Client();

		try {
			s3client.deleteObject(S3StorageConfig.getBucket(), object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
