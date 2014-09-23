package com.esofthead.mycollab.vaadin.resources.file;

import java.io.File;

import com.esofthead.mycollab.configuration.FileStorageConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.configuration.StorageConfiguration;
import com.esofthead.mycollab.vaadin.resources.VaadinResource;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public class VaadinFileResource implements VaadinResource {

	@Override
	public Resource getStreamResource(String documentPath) {
		return new FileStreamDownloadResource(documentPath);
	}

	@Override
	public Resource getImagePreviewResource(String documentPath) {
		StorageConfiguration storageConfiguration = SiteConfiguration
				.getStorageConfiguration();

		return new FileResource(new File(
				storageConfiguration.getResourcePath(documentPath)));
	}

	@Override
	public Resource getLogoResource(String logoId, int size) {
		FileStorageConfiguration fileStorageConfiguration = (FileStorageConfiguration) SiteConfiguration
				.getStorageConfiguration();
		File logoFile = fileStorageConfiguration.getLogoFile(logoId, size);
		return (logoFile != null) ? new FileResource(logoFile)
				: MyCollabResource.newResource("icons/logo.png");
	}

	@Override
	public Resource getAvatarResource(String avatarId, int size) {
		FileStorageConfiguration fileStorageConfiguration = (FileStorageConfiguration) SiteConfiguration
				.getStorageConfiguration();
		File avatarFile = fileStorageConfiguration
				.getAvatarFile(avatarId, size);
		return (avatarFile != null) ? new FileResource(avatarFile)
				: MyCollabResource.newResource("icons/default_user_avatar_"
						+ size + ".png");
	}

}
