package com.esofthead.mycollab.vaadin.resources.s3;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import com.esofthead.mycollab.vaadin.resources.VaadinResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public class VaadinS3Resource implements VaadinResource {

	@Override
	public Resource getStreamResource(String documentPath) {
		return new S3StreamDownloadResource(documentPath);
	}

	@Override
	public Resource getImagePreviewResource(String documentPath) {
		StorageConfiguration storageConfiguration = SiteConfiguration
				.getStorageConfiguration();

		return new ExternalResource(
				storageConfiguration.getResourcePath(documentPath));
	}

	@Override
	public Resource getLogoResource(String logoId, int size) {
		return new ExternalResource(SiteConfiguration.getStorageConfiguration()
				.getLogoPath(logoId, size));
	}

	@Override
	public Resource getAvatarResource(String avatarId, int size) {
		return new ExternalResource(SiteConfiguration.getStorageConfiguration()
				.getAvatarPath(avatarId, size));
	}

}
