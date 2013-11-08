/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
