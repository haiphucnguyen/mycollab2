package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.terminal.Resource;

public class StreamDownloadResourceFactory {
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

	public static Resource getImagePreviewResource(String documentPath) {
		if (StorageSetting.isFileStorage()) {
			return new ImagePreviewResource(documentPath);
		} else if (StorageSetting.isS3Storage()) {
			return new ImagePreviewResource(documentPath);
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}
}
