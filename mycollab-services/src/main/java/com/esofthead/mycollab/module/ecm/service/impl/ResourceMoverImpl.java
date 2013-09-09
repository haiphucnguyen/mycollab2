package com.esofthead.mycollab.module.ecm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.ResourceType;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceMover;
import com.esofthead.mycollab.module.ecm.service.ResourceService;

@Service
public class ResourceMoverImpl implements ResourceMover {

	private static Logger log = LoggerFactory
			.getLogger(ResourceMoverImpl.class);

	@Autowired
	private ExternalResourceService externalResourceService;

	@Autowired
	private ResourceService resourceService;

	private void moveResourceInDifferentStoreage(Resource srcRes,
			Resource destRes, String userMove) {

	}

	private void moveFile(Content srcRes, Resource destRes) {
		// get input stream of download

		// upload to dest source

		// delete src resource
	}

	/**
	 * destRes must be instanceof Folder
	 */
	@Override
	public void moveResource(Resource srcRes, Resource destRes, String userMove) {
		ResourceType srcType = ResourceUtils.getType(srcRes);
		ResourceType destType = ResourceUtils.getType(destRes);

		if (destRes instanceof Content)
			throw new MyCollabException(
					"You cant move somethings to content path.That is impossible.");

		if (srcType == ResourceType.MyCollab
				&& destType == ResourceType.MyCollab) {
			resourceService.moveResource(srcRes.getPath(), destRes.getPath(),
					userMove);
		} else if (srcType == destType && srcType != ResourceType.MyCollab) {
			// externalResourceService.move(drive, fromPath, toPath);
		} else {

		}
	}
}
