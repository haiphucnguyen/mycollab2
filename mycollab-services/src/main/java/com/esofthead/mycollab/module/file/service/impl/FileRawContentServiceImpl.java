package com.esofthead.mycollab.module.file.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.FileStorageConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.service.RawContentService;

public class FileRawContentServiceImpl implements RawContentService {

	private static final int BUFFER_SIZE = 1024;

	private static Logger log = LoggerFactory
			.getLogger(FileRawContentServiceImpl.class);

	private File baseFolder;

	public FileRawContentServiceImpl() {
		if (!SiteConfiguration.isSupportFileStorage()) {
			throw new IllegalArgumentException(
					"Setting does not support file storage");
		}

		baseFolder = FileStorageConfiguration.baseContentFolder;
	}

	@Override
	public void saveContent(String objectPath, InputStream stream) {
		int startFileNameIndex = objectPath.lastIndexOf("/");
		if (startFileNameIndex > 0) {
			/*
			 * make sure the directory exist
			 */
			String folderPath = objectPath.substring(0, startFileNameIndex);
			File file = new File(baseFolder, folderPath);
			if (!file.exists() && !file.mkdirs()) {
				throw new RuntimeException("Create directory fail");
			}
		}

		try {
			BufferedOutputStream outStream = new BufferedOutputStream(
					new FileOutputStream(new File(baseFolder, objectPath)));
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
	public InputStream getContentStream(String objectPath) {
		try {
			File file = new File(baseFolder, objectPath);
			return new FileInputStream(file);
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public void removeContent(String object) {
		try {
			File file = new File(baseFolder, object);
			if (file.exists()) {
				if (file.isDirectory()) {
					FileUtils.deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public void rename(String oldPath, String newPath) {
		File file = new File(baseFolder, oldPath);
		if (file.exists()) {
			boolean result = file
					.renameTo(new File(baseFolder + "/" + newPath));
			if (!result) {
				log.error("Can not rename old path {} to new path {}", oldPath,
						newPath);
			}
		} else {
			log.error(
					"Can not rename old path {} to new path {} because file is not existed",
					oldPath, newPath);
		}
	}

	@Override
	public void moveContent(String oldPath, String destinationPath) {
		try {
			File src = new File(baseFolder + "/" + oldPath);
			File dest = new File(baseFolder + "/" + destinationPath);

			if (!src.exists()) {
				log.debug("Source: {} is not existed", src.getPath());
				return;
			}

			if (dest.exists()) {
				FileUtils.deleteQuietly(dest);
			}

			if (src.isFile()) {
				FileUtils.moveFile(src, dest);
			} else {
				FileUtils.moveDirectory(src, dest);
			}
		} catch (IOException e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public long getSize(String path) {
		File file = new File(baseFolder + "/" + path);
		if (file.exists()) {
			if (file.isFile()) {
				return FileUtils.sizeOf(file);
			} else if (file.isDirectory()) {
				return FileUtils.sizeOfDirectory(file);
			} else {
				return 0;
			}
		}

		return 0;
	}
}
