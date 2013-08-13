package com.esofthead.mycollab.module.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Migrate {
	private static String BASE_PATH = "/Users/haiphucnguyen/Downloads/mycollab";
	private static AmazonRawContentServiceImpl amazonRawContentServiceImpl = new AmazonRawContentServiceImpl();

	public static void main(String[] args) throws FileNotFoundException {
		File legacyFolder = new File(BASE_PATH);
		migrateFolder(legacyFolder);
	}

	private static void migrateFolder(File folder) throws FileNotFoundException {
		File[] childFiles = folder.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				migrateFolder(childFile);
			} else {
				String contentPath = childFile.getAbsolutePath().substring(
						BASE_PATH.length() + 1);
				amazonRawContentServiceImpl.saveContent(contentPath,
						new FileInputStream(childFile));
			}
		}
	}
}
