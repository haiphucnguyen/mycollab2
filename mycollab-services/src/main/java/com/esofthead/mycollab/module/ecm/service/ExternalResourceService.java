package com.esofthead.mycollab.module.ecm.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ExternalResourceService extends IService {
	List<Resource> getResources(ExternalDrive drive, String path);

	List<ExternalFolder> getSubFolders(ExternalDrive drive, String path);
}
