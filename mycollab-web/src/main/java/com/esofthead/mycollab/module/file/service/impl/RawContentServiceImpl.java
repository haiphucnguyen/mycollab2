package com.esofthead.mycollab.module.file.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.file.service.RawContentService;

public class RawContentServiceImpl implements RawContentService {
	private static final int BUFFER_SIZE = 1024;

	private static Logger log = LoggerFactory
			.getLogger(RawContentServiceImpl.class);

	private static File baseContentFolder;

	static {
		String userFolder = System.getProperty("user.home");
		baseContentFolder = new File(userFolder + "/.mycollab");
		baseContentFolder.mkdirs();

		log.debug("Create content folder for mycollab application at "
				+ baseContentFolder.getAbsolutePath());
	}

	public void saveContent(String objectPath, InputStream stream) {
		int startFileNameIndex = objectPath.lastIndexOf(File.separatorChar);
		if (startFileNameIndex > 0) {
			/*
			 * make sure the directory exist
			 */
			String folderPath = objectPath.substring(0, startFileNameIndex);
			File file = new File(baseContentFolder, folderPath);
			if (!file.exists() && !file.mkdirs()) {
				throw new RuntimeException("Create directory fail");
			}
		}

		try {
			BufferedOutputStream outStream = new BufferedOutputStream(
					new FileOutputStream(
							new File(baseContentFolder, objectPath)));
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

	public InputStream getContent(String objectPath) {
		try {
			return new FileInputStream(objectPath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void removeContent(String object) {
		try {
			File file = new File(object);
			if (file.exists()) {
				if (file.isDirectory())
					FileUtils.deleteDirectory(file);
				else
					file.delete();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
