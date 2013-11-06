package com.esofthead.mycollab.core.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static File detectFolderByName(File homeDir, String foldername) {
		File[] listFiles = homeDir.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				if (file.getName().equals(foldername)) {
					log.debug("Detect folder name {} at path {}", new Object[] {
							foldername, file.getAbsolutePath() });
					return file;
				} else {
					File resultFile = detectFolderByName(file, foldername);
					if (resultFile != null) {
						return resultFile;
					}
				}
			} else {
				continue;
			}
		}

		return null;
	}

	public static File detectFileByName(File homeDir, String filename) {
		File[] listFiles = homeDir.listFiles();
		try {
			for (File file : listFiles) {
				if (file.isFile()) {
					if (file.getName().equals(filename)) {
						log.debug(
								"Detect file name {} at path {}",
								new Object[] { filename, file.getAbsolutePath() });
						return file;
					}
				} else {
					log.debug("Search folder {}", file.getAbsolutePath());
					File resultFile = detectFileByName(file, filename);
					if (resultFile != null) {
						return resultFile;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
