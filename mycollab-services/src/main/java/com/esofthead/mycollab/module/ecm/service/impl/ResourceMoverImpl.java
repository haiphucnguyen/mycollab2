package com.esofthead.mycollab.module.ecm.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.ResourceType;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceMover;
import com.esofthead.mycollab.module.ecm.service.ResourceService;

@Service
public class ResourceMoverImpl implements ResourceMover {

	private static Logger log = LoggerFactory
			.getLogger(ResourceMoverImpl.class);

	@Autowired
	private ResourceService resourceService;

	private void moveResourceInDifferentStorage(Resource srcRes,
			Resource destRes, String userMove) {
		String destMovePath = destRes.getPath() + "/" + srcRes.getName();

		if (srcRes instanceof Folder) {
			ExternalResourceService destService = ResourceUtils
					.getExternalResourceService(ResourceUtils.getType(destRes));
			Folder createdFolder = destService.createFolder(
					ResourceUtils.getExternalDrive(destRes), destMovePath);

			ExternalResourceService srcService = ResourceUtils
					.getExternalResourceService(ResourceUtils.getType(srcRes));
			List<Resource> lstRes = srcService.getResources(
					ResourceUtils.getExternalDrive(srcRes), srcRes.getPath());
			for (Resource res : lstRes) {
				if (res instanceof Folder)
					moveResourceInDifferentStorage(res, createdFolder, userMove);
				else
					moveFile((Content) res, createdFolder, userMove);
			}
		} else {
			moveFile((Content) srcRes, destRes, userMove);
		}
	}

	private void moveFile(Content srcRes, Resource destRes, String userMove) {
		// get input stream of download
		String destMovePath = destRes.getPath() + "/" + srcRes.getName();
		String srcPath = srcRes.getPath();

		ExternalResourceService srcService = ResourceUtils
				.getExternalResourceService(ResourceUtils.getType(srcRes));

		InputStream in = srcService.download(
				ResourceUtils.getExternalDrive(srcRes), srcRes.getPath());

		// upload to dest source
		ExternalResourceService destService = ResourceUtils
				.getExternalResourceService(ResourceUtils.getType(destRes));
		srcRes.setPath(destMovePath);
		destService.saveContent(ResourceUtils.getExternalDrive(destRes),
				srcRes, in);

		// delete src resource
		srcService.deleteResource(ResourceUtils.getExternalDrive(srcRes),
				srcPath);

	}

	private void moveResourceMyCollabToDifferentStorage(Resource srcRes,
			Resource destRes, String userMove) {
		String destMovePath = destRes.getPath() + "/" + srcRes.getName();

		if (srcRes instanceof Folder) {
			ExternalResourceService destService = ResourceUtils
					.getExternalResourceService(ResourceUtils.getType(destRes));
			Folder createdFolder = destService.createFolder(
					ResourceUtils.getExternalDrive(destRes), destMovePath);

			List<Resource> lstRes = resourceService.getResources(srcRes
					.getPath());
			for (Resource res : lstRes) {
				if (res instanceof Folder)
					moveResourceMyCollabToDifferentStorage(res, createdFolder,
							userMove);
				else
					moveFile((Content) res, createdFolder, userMove);
			}
		} else {
			moveFile((Content) srcRes, destRes, userMove);
		}
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
			moveResourceInDifferentStorage(srcRes, destRes, userMove);
		} else {
			// tu myCollab toi' cac storage khac
			moveResourceMyCollabToDifferentStorage(srcRes, destRes, userMove);
		}
	}
}
