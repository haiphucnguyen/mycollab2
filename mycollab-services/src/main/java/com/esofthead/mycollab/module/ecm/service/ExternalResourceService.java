package com.esofthead.mycollab.module.ecm.service;

import java.io.InputStream;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ExternalResourceService extends IService {
	List<Resource> getResources(ExternalDrive drive, String path);

	List<ExternalFolder> getSubFolders(ExternalDrive drive, String path);

	Resource getcurrentResourceByPath(ExternalDrive drive, String path);

	Folder getParentResourceFolder(ExternalDrive drive, String childPath);

	Folder createFolder(ExternalDrive drive, String path);

	void saveContent(ExternalDrive drive, Content content, InputStream in);

	void rename(ExternalDrive drive, String oldPath, String newPath);

	void deleteResource(ExternalDrive drive, String path);

	void download(ExternalDrive drive, String path);

	void move(ExternalDrive drive, String fromPath, String toPath);
}
