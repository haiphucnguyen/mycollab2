package com.esofthead.mycollab.module.ecm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxEntry.File;
import com.dropbox.core.DbxEntry.WithChildren;
import com.dropbox.core.DbxRequestConfig;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DropboxResourceService;

@Service
public class DropboxResourceServiceImpl implements DropboxResourceService {
	private static Logger log = LoggerFactory
			.getLogger(DropboxResourceServiceImpl.class);

	@Override
	public List<Resource> getResources(ExternalDrive drive, String path) {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			DbxRequestConfig requestConfig = new DbxRequestConfig(
					"MyCollab/1.0", null);
			DbxClient client = new DbxClient(requestConfig,
					drive.getAccesstoken());
			WithChildren children = client.getMetadataWithChildren(path);
			if (children.children != null) {
				for (DbxEntry entry : children.children) {
					if (entry.isFile()) {
						ExternalContent resource = new ExternalContent();
						resource.setStorageName(StorageNames.DROPBOX);
						resource.setExternalDrive(drive);
						Date lastModifiedDate = ((File) entry).lastModified;
						Calendar createdDate = new GregorianCalendar();
						createdDate.setTime(lastModifiedDate);
						resource.setSize(Double
								.parseDouble(((File) entry).numBytes / 1024
										+ ""));
						resource.setCreated(createdDate);
						resource.setPath(entry.path);
						resources.add(resource);
					} else if (entry.isFolder()) {
						ExternalFolder resource = new ExternalFolder();
						resource.setStorageName(StorageNames.DROPBOX);
						resource.setExternalDrive(drive);
						resource.setPath(entry.path);
						resources.add(resource);
					} else {
						log.error("Do not support dropbox resource except file or folder");
					}
				}
			}
		} catch (Exception e) {
			log.error("Error when get dropbox resource", e);
		}

		return resources;
	}

	@Override
	public List<ExternalFolder> getSubFolders(ExternalDrive drive, String path) {
		List<ExternalFolder> subFolders = new ArrayList<ExternalFolder>();

		try {
			DbxRequestConfig requestConfig = new DbxRequestConfig(
					"MyCollab/1.0", null);
			DbxClient client = new DbxClient(requestConfig,
					drive.getAccesstoken());
			WithChildren children = client.getMetadataWithChildren(path);
			if (children.children != null) {
				for (DbxEntry entry : children.children) {
					if (entry.isFolder()) {
						ExternalFolder resource = new ExternalFolder();
						resource.setStorageName(StorageNames.DROPBOX);
						resource.setPath(entry.path);
						resource.setExternalDrive(drive);
						subFolders.add(resource);
					}

				}
			}
		} catch (Exception e) {
			log.error("Error when get dropbox resource", e);
		}

		return subFolders;
	}

	public static void main(String[] args) {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			ExternalDrive drive = new ExternalDrive();
			drive.setAccesstoken("2qtgoUhfNbsAAAAAAAAAARkETVWVUdFB4-vExevabzAsv8RcTHocOoXmvXpRWsrz");
			DbxRequestConfig requestConfig = new DbxRequestConfig(
					"MyCollab/1.0", null);
			DbxClient client = new DbxClient(requestConfig,
					drive.getAccesstoken());
			WithChildren children = client
					.getMetadataWithChildren("/Camera Uploads");
			if (children.children != null) {
				for (DbxEntry entry : children.children) {
					if (entry.isFile()) {
						ExternalContent resource = new ExternalContent();
						resource.setStorageName(StorageNames.DROPBOX);
						resource.setExternalDrive(drive);
						Date lastModifiedDate = ((File) entry).lastModified;
						Calendar createdDate = new GregorianCalendar();
						createdDate.setTime(lastModifiedDate);
						resource.setSize(Double
								.parseDouble(((File) entry).numBytes / 1024
										+ ""));
						resource.setCreated(createdDate);
						resource.setPath(entry.path);
						resources.add(resource);
					} else if (entry.isFolder()) {
						ExternalFolder resource = new ExternalFolder();
						resource.setStorageName(StorageNames.DROPBOX);
						resource.setExternalDrive(drive);
						resource.setPath(entry.path);
						resources.add(resource);
					} else {
						log.error("Do not support dropbox resource except file or folder");
					}
				}
			}

			System.out.println("Resources: " + resources.size());
		} catch (Exception e) {
			log.error("Error when get dropbox resource", e);
		}
	}

}
