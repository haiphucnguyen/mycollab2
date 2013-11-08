/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
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

		for (File file : listFiles) {
			if (file.isFile()) {
				if (file.getName().equals(filename)) {
					log.debug("Detect file name {} at path {}", new Object[] {
							filename, file.getAbsolutePath() });
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

		return null;
	}
}
