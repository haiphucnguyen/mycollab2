package com.esofthead.mycollab.module.ecm.dao;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ContentJcrDao {
	void saveContent(Content content); // allow save an object

	Content getResource(String path); 

	void removeResource(String path);
	
	List<Resource> getResources(String path);
}
