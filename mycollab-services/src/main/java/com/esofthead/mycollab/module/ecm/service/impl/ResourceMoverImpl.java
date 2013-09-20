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
			Folder createdFolder = null;
			List<Resource> lstRes = null;

			if (ResourceUtils.getType(destRes) != ResourceType.MyCollab) {
				ExternalResourceService destService = ResourceUtils
						.getExternalResourceService(ResourceUtils
								.getType(destRes));
				createdFolder = destService.createFolder(
						ResourceUtils.getExternalDrive(destRes), destMovePath);
			} else {
				createdFolder = resourceService.createNewFolder(
						destRes.getPath(), srcRes.getName(), userMove);
			}

			if (ResourceUtils.getType(srcRes) != ResourceType.MyCollab) {
				ExternalResourceService srcService = ResourceUtils
						.getExternalResourceService(ResourceUtils
								.getType(srcRes));
				lstRes = srcService.getResources(
						ResourceUtils.getExternalDrive(srcRes),
						srcRes.getPath());
			} else {
				lstRes = resourceService.getResources(srcRes.getPath());
			}

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

		InputStream in = null;
		ExternalResourceService srcService = null;
		if (ResourceUtils.getType(srcRes) != ResourceType.MyCollab) {
			srcService = ResourceUtils.getExternalResourceService(ResourceUtils
					.getType(srcRes));

			in = srcService.download(ResourceUtils.getExternalDrive(srcRes),
					srcRes.getPath());
		} else {
			in = resourceService.getContentStream(srcPath);
		}

		// upload to dest source
		srcRes.setPath(destMovePath);
		if (ResourceUtils.getType(destRes) != ResourceType.MyCollab) {
			ExternalResourceService destService = ResourceUtils
					.getExternalResourceService(ResourceUtils.getType(destRes));
			destService.saveContent(ResourceUtils.getExternalDrive(destRes),
					srcRes, in);
		} else {
			
			//TODO: SHOULD CALL MOVE METHOD NOT SAVE
//			resourceService.saveContent(srcRes, userMove, in);
		}
	}

	private boolean checkIsTheSameAccountInStorage(Resource srcRes,
			Resource destRes) {
		if (ResourceUtils.getType(srcRes) == ResourceUtils.getType(destRes)
				&& ResourceUtils
						.getExternalDrive(srcRes)
						.getAccesstoken()
						.equals(ResourceUtils.getExternalDrive(destRes)
								.getAccesstoken())) {
			return true;
		}
		return false;
	}

	private boolean isDuplicateFileName(Resource srcRes, Resource destRes) {
		if (ResourceUtils.getType(destRes) == ResourceType.MyCollab) {
			List<Resource> lstRes = resourceService.getResources(destRes
					.getPath());
			for (Resource res : lstRes) {
				if (srcRes.getName().equals(res.getName()))
					return true;
			}
		} else {
			ExternalResourceService service = ResourceUtils
					.getExternalResourceService(ResourceUtils.getType(destRes));
			List<Resource> lstRes = service.getResources(
					ResourceUtils.getExternalDrive(destRes), destRes.getPath());
			for (Resource res : lstRes) {
				if (srcRes.getName().equals(res.getName())) {
					return true;
				}
			}
		}
		return false;
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
		if (isDuplicateFileName(srcRes, destRes)) {
			throw new MyCollabException(
					"Please check duplicate file, before move");
		}

		if (srcType == ResourceType.MyCollab
				&& destType == ResourceType.MyCollab) {
			resourceService.moveResource(srcRes.getPath(), destRes.getPath(),
					userMove);
		} else if (srcType == destType && srcType != ResourceType.MyCollab) {
			if (checkIsTheSameAccountInStorage(srcRes, destRes)) {
				ExternalResourceService service = ResourceUtils
						.getExternalResourceService(ResourceUtils
								.getType(srcRes));
				service.move(ResourceUtils.getExternalDrive(srcRes),
						srcRes.getPath(),
						destRes.getPath() + "/" + srcRes.getName());
			} else {
				moveResourceInDifferentStorage(srcRes, destRes, userMove);
				// delete src resource
				ExternalResourceService srcService = ResourceUtils
						.getExternalResourceService(ResourceUtils
								.getType(srcRes));
				srcService.deleteResource(
						ResourceUtils.getExternalDrive(srcRes),
						srcRes.getPath());
			}
		} else {
			moveResourceInDifferentStorage(srcRes, destRes, userMove);

			if (ResourceUtils.getType(srcRes) != ResourceType.MyCollab) {
				ExternalResourceService srcService = ResourceUtils
						.getExternalResourceService(ResourceUtils
								.getType(srcRes));
				srcService.deleteResource(
						ResourceUtils.getExternalDrive(srcRes),
						srcRes.getPath());
			} else {
				resourceService.removeResource(srcRes.getPath(), userMove);
			}

		}
	}
}
