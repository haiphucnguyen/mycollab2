package com.esofthead.mycollab.module.ecm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
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
					Resource resource = null;
					if (entry.isFile()) {
						resource = new ExternalContent();
						((ExternalContent) resource)
								.setStorageName(StorageNames.DROPBOX);
						resource.setPath(entry.path);
					} else if (entry.isFolder()) {
						resource = new ExternalFolder();
						((ExternalContent) resource)
								.setStorageName(StorageNames.DROPBOX);
						resource.setPath(entry.path);
					} else {
						log.error("Do not support dropbox resource except file or folder");
					}
					resources.add(resource);
				}
			}
		} catch (Exception e) {
			log.error("Error when get dropbox resource", e);
		}

		return resources;
	}

}
