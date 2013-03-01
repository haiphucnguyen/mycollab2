package com.esofthead.mycollab.module.file.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.esofthead.mycollab.module.file.FileStorageConfig;
import com.esofthead.mycollab.module.file.service.RawContentService;

public class RawContentServiceImpl implements RawContentService {

	private static final int BUFFER_SIZE = 1024;

	@Override
	public void saveContent(String objectPath, InputStream stream) {
		int startFileNameIndex = objectPath.lastIndexOf(File.separatorChar);
		if (startFileNameIndex > 0) {
			/*
			 * make sure the directory exist
			 */
			String folderPath = objectPath.substring(0, startFileNameIndex);
			File file = new File(FileStorageConfig.baseContentFolder,
					folderPath);
			if (!file.exists() && !file.mkdirs()) {
				throw new RuntimeException("Create directory fail");
			}
		}

		try {
			BufferedOutputStream outStream = new BufferedOutputStream(
					new FileOutputStream(new File(
							FileStorageConfig.baseContentFolder, objectPath)));
			byte[] buffer = new byte[BUFFER_SIZE];
			int byteRead = 0;

			while ((byteRead = stream.read(buffer)) >= 0) {
				outStream.write(buffer, 0, byteRead);
			}
			outStream.flush();
			outStream.close();

			stream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public InputStream getContent(String objectPath) {
		try {
			File file = new File(FileStorageConfig.baseContentFolder,
					objectPath);
			System.out.println("File: " + file.getAbsolutePath());
			return new FileInputStream(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeContent(String object) {
		try {
			File file = new File(FileStorageConfig.baseContentFolder, object);
			if (file.exists()) {
				if (file.isDirectory()) {
					FileUtils.deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
