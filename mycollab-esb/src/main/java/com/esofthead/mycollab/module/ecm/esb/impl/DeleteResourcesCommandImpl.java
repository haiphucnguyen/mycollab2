package com.esofthead.mycollab.module.ecm.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.esb.DeleteResourcesCommand;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.file.service.RawContentService;

@Component
public class DeleteResourcesCommandImpl implements DeleteResourcesCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteResourcesCommandImpl.class);

	@Autowired
	private RawContentService rawContentService;
	
	@Autowired
	private DriveInfoService driveInfoService;

	@Override
	public void removeResource(String path, String userDelete,
			Integer sAccountId) {
		log.debug("Delete resource path {} by user {}", path, userDelete);

		long size = rawContentService.getSize(path);
		if (size > 0) {
			DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);
			
		}
	}

}
