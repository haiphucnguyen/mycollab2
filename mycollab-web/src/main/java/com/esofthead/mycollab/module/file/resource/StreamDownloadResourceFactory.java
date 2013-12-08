/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.file.resource;

import java.io.File;
import java.util.List;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;

public class StreamDownloadResourceFactory {

	public static Resource getStreamResource(String documentPath) {
		if (SiteConfiguration.isSupportFileStorage()) {
			return new StreamDownloadResource(documentPath);
		} else if (SiteConfiguration.isSupportS3Storage()) {
			return new S3StreamDownloadResource(documentPath);
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}

	public static Resource getStreamResourceSupportExtDrive(
			List<com.esofthead.mycollab.module.ecm.domain.Resource> lstRes,
			boolean isSearchAction) {
		if (lstRes == null || lstRes.isEmpty())
			return null;
		if (lstRes.size() == 1) {
			String name = (lstRes.get(0) instanceof Folder) ? lstRes.get(0)
					.getName() + ".zip" : lstRes.get(0).getName();
			return new StreamResource(
					new StreamDownloadResourceSupportExtDrive(lstRes,
							isSearchAction), name);
		}
		return new StreamResource(new StreamDownloadResourceSupportExtDrive(
				lstRes, isSearchAction), "out.zip");
	}

	public static Resource getImagePreviewResource(String documentPath) {
		StorageConfiguration storageConfiguration = SiteConfiguration
				.getStorageConfiguration();
		if (SiteConfiguration.isSupportFileStorage()) {
			return new FileResource(new File(
					storageConfiguration.generateResourcePath(documentPath)));
		} else if (SiteConfiguration.isSupportS3Storage()) {
			return new ExternalResource(
					storageConfiguration.generateResourcePath(documentPath));
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}
}
