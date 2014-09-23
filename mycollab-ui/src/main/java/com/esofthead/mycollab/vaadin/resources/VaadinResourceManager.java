package com.esofthead.mycollab.vaadin.resources;

import com.esofthead.mycollab.configuration.StorageManager;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.resources.VaadinResource;
import com.esofthead.mycollab.vaadin.resources.file.VaadinFileResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public class VaadinResourceManager {

	private static final String S3_CLS = "com.esofthead.mycollab.vaadin.resources.s3.VaadinS3Resource";

	@SuppressWarnings("unchecked")
	public static VaadinResource getResourceManager() {
		if (StorageManager.isFileStorage()) {
			return new VaadinFileResource();
		} else if (StorageManager.isS3Storage()) {
			try {
				Class<VaadinResource> cls = (Class<VaadinResource>) Class
						.forName(S3_CLS);
				return cls.newInstance();
			} catch (Exception e) {
				throw new MyCollabException(
						"Exception when load s3 resource file", e);
			}
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}
	}
}
