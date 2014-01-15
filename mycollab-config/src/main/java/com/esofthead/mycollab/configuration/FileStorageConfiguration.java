/**
 * This file is part of mycollab-config.
 *
 * mycollab-config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-config.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.configuration;

import java.io.File;

/**
 * Configuration of file system storage mode
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FileStorageConfiguration implements StorageConfiguration {

	public static File baseContentFolder;

	static {
		String userFolder = System.getProperty("user.home");
		baseContentFolder = new File(userFolder + "/.mycollab");
		baseContentFolder.mkdirs();
	}

	private FileStorageConfiguration() {
		File avatarFolder = new File(baseContentFolder, "avatar");
		avatarFolder.mkdirs();
	}

	public static FileStorageConfiguration build() {
		return new FileStorageConfiguration();
	}

	@Override
	public String generateAvatarPath(String userAvatarId, int size) {
		return SiteConfiguration.getSiteUrl("app") + "avatar/" + userAvatarId
				+ "/" + size;
	}

	@Override
	public String generateResourcePath(String documentPath) {
		return baseContentFolder.getPath() + "/" + documentPath;
	}

	public File getAvatarFile(String username, int size) {
		File userAvatarFile = new File(baseContentFolder, "/avatar/" + username
				+ "_" + size + ".png");
		if (userAvatarFile.exists()) {
			return userAvatarFile;
		} else {
			return null;
		}
	}

}
