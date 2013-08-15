package com.esofthead.mycollab.module.file.resource;

import java.io.File;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
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

	public static Resource getStreamFolderResource(String[] documentPath,
			boolean isSearchAction) {
		return new StreamResource(new StreamFolderDownloadResource(
				documentPath, isSearchAction), "out.zip",
				AppContext.getApplication());

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
