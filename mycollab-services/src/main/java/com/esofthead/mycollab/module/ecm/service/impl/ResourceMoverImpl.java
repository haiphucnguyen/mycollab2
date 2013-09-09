package com.esofthead.mycollab.module.ecm.service.impl;

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
	private ExternalResourceService externalResourceService;

	@Autowired
	private ResourceService resourceService;

	private void moveResourceInTheSameExternalStorages(Resource srcRes,
			Resource destRes, String userMove) {
		if (ResourceUtils
				.getExternalDrive(srcRes)
				.getAccesstoken()
				.equals(ResourceUtils.getExternalDrive(destRes)
						.getAccesstoken())) {
			log.debug("Move in the same connection to an account ExternalDrive----");

		} else {
			log.debug("Move in the same Drive but different account----");
			if (checkDuplicateName(srcRes, destRes)) {
				throw new MyCollabException(
						"Duplicate file/folder , please check it before move");
			}
			if (srcRes instanceof Folder) {
				recursiveMoveResourceFolder(srcRes, destRes, userMove);
			} else {
				moveFile((Content) srcRes, destRes);
			}
		}
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
			log.debug("Move in the same external Storges-------------dropbox,googleDrive");
			moveResourceInTheSameExternalStorages(srcRes, destRes, userMove);
		} else {
			if (srcType == ResourceType.MyCollab
					&& destType == ResourceType.Dropbox) {
				log.debug("Move from MyCollab to Dropbox -------");
				if (srcRes instanceof Folder) {
					recursiveMoveResourceFolder(srcRes, destRes, userMove);
				} else {

				}
			} else if (srcType == ResourceType.Dropbox
					&& destType == ResourceType.MyCollab) {
				log.debug("Move from Dropbox to MyCollab --------");
				if (srcRes instanceof Folder) {
					recursiveMoveResourceFolder(srcRes, destRes, userMove);
				} else {

				}
			} else {
				throw new MyCollabException(
						"Not yet support any others external-Drives!");
			}
		}
	}

	/**
	 * Because Problem ocur when move Dropbox <-> Mycollab so only check specify
	 * situation
	 * 
	 * @param createRes
	 *            is Resource want to move to destionation
	 * @param desPath
	 * @return
	 */
	private boolean checkDuplicateName(Resource srcRes, Resource destRes) {
		String desResourcePath = (destRes.getPath().equals("/")) ? "" : destRes
				.getPath();
		String desPath = desResourcePath + "/" + srcRes.getName();
		// Dropbox -> MyCollab
		if (ResourceUtils.getType(srcRes) == ResourceType.Dropbox
				&& ResourceUtils.getType(destRes) == ResourceType.MyCollab) {
			return (resourceService.getResource(desPath) == null) ? false
					: true;
		}
		// MyCollab -> Dropbox
		if (ResourceUtils.getType(srcRes) == ResourceType.MyCollab
				&& ResourceUtils.getType(destRes) == ResourceType.Dropbox) {
			Resource res = externalResourceService.getCurrentResourceByPath(
					ResourceUtils.getExternalDrive(destRes), desPath);
			return (res == null) ? false : true;
		}
		// Dropbox -> Dropbox in defferent account
		if (ResourceUtils.getType(srcRes) == ResourceType.Dropbox
				&& ResourceUtils.getType(destRes) == ResourceType.Dropbox) {
			Resource res = externalResourceService.getCurrentResourceByPath(
					ResourceUtils.getExternalDrive(destRes), desPath);
			return (res == null) ? false : true;
		}
		// default is mycollab -> mycollab , api can handle it
		return false;
	}

	/**
	 * @param scrRes
	 *            is MyCollab
	 * @param descRes
	 *            is Dropbox
	 * @param userMove
	 */

	private void recursiveMoveResourceFolder(Resource scrRes, Resource descRes,
			String userMove) {
		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();

		if (scrRes instanceof Folder) {

		} else if (scrRes instanceof Content) {

		} else {
			throw new MyCollabException("Not support, it' a strange file type");
		}
	}
}
