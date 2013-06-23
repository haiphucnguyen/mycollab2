package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;

public class StreamDownloadResourceFactory {
	public static Resource getAccountStreamResource(String documentPath) {
		if (StorageSetting.isFileStorage()) {
			return new StreamDownloadResource(documentPath,
					AppContext.getAccountId());
		} else if (StorageSetting.isS3Storage()) {
			return new S3StreamDownloadResource(documentPath);
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}

	public static Resource getStreamResource(String documentPath) {
		if (StorageSetting.isFileStorage()) {
			return new StreamDownloadResource(documentPath);
		} else if (StorageSetting.isS3Storage()) {
			return new S3StreamDownloadResource(documentPath);
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}

	public static Resource getStreamFolderResource(String documentPath) {
		return new StreamResource(
				new StreamFolderDownloadResource(documentPath), "out.zip",
				AppContext.getApplication());

	}

	public static Resource getImagePreviewResource(String documentPath) {
		if (StorageSetting.isFileStorage()) {
			return new ImagePreviewResource(documentPath);
		} else if (StorageSetting.isS3Storage()) {
			return new ExternalResource(
					S3StorageConfig.getResourceLink(documentPath));
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}
}
