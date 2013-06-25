package com.esofthead.mycollab.module.ecm.dao;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ContentJcrDao {

	void saveContent(Content content, String createdUser);

	void createFolder(Folder folder, String createdUser);

	Resource getResource(String path);

	void removeResource(String path);

	List<Resource> getResources(String path);

	List<Folder> getSubFolders(String path);
	
	List<Resource> searchResourcesByName(String resourceName);
}
