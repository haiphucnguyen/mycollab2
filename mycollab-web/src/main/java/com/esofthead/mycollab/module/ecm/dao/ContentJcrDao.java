package com.esofthead.mycollab.module.ecm.dao;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ContentJcrDao {
	
	void saveContent(Content content); 

	void createFolder(Folder folder);

	Resource getResource(String path); 

	void removeResource(String path);
	
	List<Resource> getResources(String path);
}
