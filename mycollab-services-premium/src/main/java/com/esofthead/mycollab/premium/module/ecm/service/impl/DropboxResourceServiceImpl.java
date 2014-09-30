package com.esofthead.mycollab.premium.module.ecm.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.UnsupportedFeatureException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DropboxResourceService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
@Service
public class DropboxResourceServiceImpl implements DropboxResourceService {

	@Override
	public List<Resource> getResources(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public List<ExternalFolder> getSubFolders(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public Resource getCurrentResourceByPath(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public Folder getParentResourceFolder(ExternalDrive drive, String childPath) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public Folder createFolder(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void saveContent(ExternalDrive drive, Content content, InputStream in) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void rename(ExternalDrive drive, String oldPath, String newPath) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void deleteResource(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");

	}

	@Override
	public InputStream download(ExternalDrive drive, String path) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");
	}

	@Override
	public void move(ExternalDrive drive, String fromPath, String toPath) {
		throw new UnsupportedFeatureException(
				"This feature is not supported except onsite mode");

	}
}
