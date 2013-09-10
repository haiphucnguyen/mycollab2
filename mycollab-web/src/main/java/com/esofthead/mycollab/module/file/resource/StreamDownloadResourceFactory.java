package com.esofthead.mycollab.module.file.resource;

import java.io.File;
import java.util.List;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;

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
							isSearchAction), name, AppContext.getApplication());
		}
		return new StreamResource(new StreamDownloadResourceSupportExtDrive(
				lstRes, isSearchAction), "out.zip", AppContext.getApplication());
	}

	public static Resource getImagePreviewResource(String documentPath) {
		StorageConfiguration storageConfiguration = SiteConfiguration
				.getStorageConfiguration();
		if (SiteConfiguration.isSupportFileStorage()) {
			return new FileResource(new File(
					storageConfiguration.generateResourcePath(documentPath)),
					AppContext.getApplication());
		} else if (SiteConfiguration.isSupportS3Storage()) {
			return new ExternalResource(
					storageConfiguration.generateResourcePath(documentPath));
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}
}
