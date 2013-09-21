package com.esofthead.mycollab.module.ecm.service;

import java.io.InputStream;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ResourceService extends IService {
	Folder createNewFolder(String baseFolderPath, String folderName,
			String createdBy);

	List<Resource> getResources(String path);

	Resource getResource(String path);

	List<Content> getContents(String path);

	List<Folder> getSubFolders(String path);

	void saveContent(Content content, String createdUser,
			InputStream refStream, Integer sAccountId);

	void removeResource(String path, String userDelete, Integer sAccountId);

	InputStream getContentStream(String path);

	void rename(String oldPath, String newPath, String userUpdate);

	List<Resource> searchResourcesByName(String baseFolderPath,
			String resourceName);

	void moveResource(String oldPath, String newPath, String userMove);

	Folder getParentFolder(String path);
}
