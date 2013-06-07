package com.esofthead.mycollab.module.ecm.service;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ResourceService {
	List<Resource> getResources(String path);

	List<Folder> getSubFolders(String path);
}
