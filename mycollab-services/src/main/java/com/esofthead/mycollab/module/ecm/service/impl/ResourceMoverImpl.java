package com.esofthead.mycollab.module.ecm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.ecm.ResourceType;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
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

	/**
	 * show what type of Dirve {0 : is MyCollab} {1 : Dropbox} {2 : GoogleDrive}
	 */
	private int srcTypeDrive;
	private int desTypeDrive;

	private void moveResourceInDifferentStorages(Resource srcRes,
			Resource destRes) {

		if (srcRes instanceof Folder) {
			if (srcRes instanceof ExternalFolder) {
				
			} else {
				
			}
		} else {
			moveFile((Content) srcRes, destRes);
		}
	}

	private void moveFile(Content srcRes, Resource destRes) {
		// get input stream of download

		// upload to dest source

		// delete src resource
	}

	/**
	 * descRes must be instanceof Folder
	 */
	@Override
	public void moveResource(Resource srcRes, Resource destRes, String userMove) {
		ResourceType srcType = ResourceUtils.getType(srcRes);
		ResourceType destType = ResourceUtils.getType(destRes);

		if (srcType == ResourceType.MyCollab
				&& destType == ResourceType.MyCollab) {

		} else if (srcType == destType && srcType != ResourceType.MyCollab) {
			moveResourceInDifferentStorages(srcRes, destRes);
		} else {

		}

		// get Instance of Resouce------------------------------
//		srcTypeDrive = getTypeOfResource(srcRes);
//		desTypeDrive = getTypeOfResource(destRes);
//
//		String desResourcePath = (destRes.getPath().equals("/")) ? "" : destRes
//				.getPath();
//		String desPath = desResourcePath + "/" + srcRes.getName();
//		String scrPath = srcRes.getPath();
//		try {
//			if (destRes instanceof Content)
//				throw new MyCollabException(
//						"You cant move somethings to content path.That is impossible.");
//
//			// MyCollab -> MyCollab : implemented
//			if (srcTypeDrive == 0 && desTypeDrive == 0) {
//				resourceService.moveResource(srcRes.getPath(),
//						destRes.getPath(), userMove);
//			} else if (srcTypeDrive == 1 && desTypeDrive == 1) {// Dropbox ->
//																// Dropbox
//				// in once Dropbox : DbxClient move
//				ExternalDrive srcDrive = getExternalDrive(srcRes);
//				ExternalDrive desDrive = getExternalDrive(destRes);
//
//				// in the same dropbox
//				if (srcDrive.getAccesstoken().equals(desDrive.getAccesstoken())) {
//					if (checkDuplicateName(srcRes, destRes))
//						throw new MyCollabException(
//								"Duplicate file/name, please check it before move!");
//					externalResourceService.move(srcDrive, srcRes.getPath(),
//							desPath);
//				} else { // Dropbox -> another Dropbox
//					if (checkDuplicateName(srcRes, destRes))
//						throw new MyCollabException(
//								"Duplicate file/name, please check it before move!");
//
//					if (srcRes instanceof ExternalContent) {
//						InputStream in = externalResourceService.download(
//								srcDrive, srcRes.getPath());
//						// reset Path of source upload
//						srcRes.setPath(desPath);
//						externalResourceService.saveContent(desDrive,
//								(Content) srcRes, in);
//					} else if (srcRes instanceof ExternalFolder) {
//						Folder createdFolder = externalResourceService
//								.createFolder(desDrive, desPath);
//						List<Resource> lstRes = externalResourceService
//								.getResources(srcDrive, srcRes.getPath());
//						for (Resource res : lstRes) {
//							recursiveToMoveDropboxToDropbox(res, createdFolder,
//									userMove);
//						}
//					} else {
//						throw new MyCollabException(
//								"Not support, it' a strange file type");
//					}
//					externalResourceService.deleteResource(srcDrive, scrPath);
//				}
//			} else { // MyCollab -> Dropbox : uploader
//				if (checkDuplicateName(srcRes, destRes))
//					throw new MyCollabException(
//							"Duplicate file/name, please check it before move!");
//
//				if (srcTypeDrive == 0 && desTypeDrive == 1) {
//					if (srcRes instanceof Folder) {
//						Folder createdFolder = externalResourceService
//								.createFolder(getExternalDrive(destRes),
//										desPath);
//						List<Resource> lstRes = resourceService
//								.getResources(srcRes.getPath());
//						if (lstRes != null) {
//							for (Resource res : lstRes) {
//								recursiveToMoveMyCollabToDropbox(res,
//										createdFolder, userMove);
//							}
//						}
//					} else if (srcRes instanceof Content) {
//						InputStream in = resourceService
//								.getContentStream(srcRes.getPath());
//						srcRes.setPath(desPath);
//						externalResourceService
//								.saveContent(getExternalDrive(destRes),
//										(Content) srcRes, in);
//					} else {
//						throw new MyCollabException(
//								"Not support, it' a strange file type");
//					}
//					resourceService.removeResource(scrPath, userMove);
//				} else { // Dropbox -> MyCollab : createNewFolder & saveContent
//					if (checkDuplicateName(srcRes, destRes))
//						throw new MyCollabException(
//								"Duplicate file/name, please check it before move!");
//
//					if (srcRes instanceof Content) {
//						InputStream in = externalResourceService.download(
//								getExternalDrive(srcRes), srcRes.getPath());
//
//						srcRes.setPath(desPath);
//						resourceService.saveContent((Content) srcRes, userMove,
//								in);
//					} else if (srcRes instanceof Folder) {
//						Folder createdFolder = resourceService.createNewFolder(
//								destRes.getPath(), srcRes.getName(), userMove);
//						List<Resource> lstRes = externalResourceService
//								.getResources(getExternalDrive(srcRes),
//										srcRes.getPath());
//						if (lstRes != null) {
//							for (Resource res : lstRes) {
//								recursiveToMoveFolderDropboxToMyCollab(res,
//										createdFolder, userMove);
//							}
//						}
//					} else {
//						throw new MyCollabException(
//								"Not support, it' a strange file type");
//					}
//					externalResourceService.deleteResource(
//							getExternalDrive(srcRes), scrPath);
//				}
//			}
//			// TODO : more handle for GoogleDrive here
//		} catch (Exception e) {
//			throw new MyCollabException(e);
//		}
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
	private boolean checkDuplicateName(Resource scrRes, Resource desRes) {
//		String desResourcePath = (desRes.getPath().equals("/")) ? "" : desRes
//				.getPath();
//		String desPath = desResourcePath + "/" + scrRes.getName();
//		// Dropbox -> MyCollab
//		if (getExternalDrive(scrRes) != null
//				&& getExternalDrive(desRes) == null) {
//			Resource res = resourceService.getResource(desPath);
//			return (res == null) ? false : true;
//		}
//		// MyCollab -> Dropbox
//		if (getExternalDrive(scrRes) == null
//				&& getExternalDrive(desRes) != null) {
//			Resource res = externalResourceService.getCurrentResourceByPath(
//					getExternalDrive(desRes), desPath);
//			return (res == null) ? false : true;
//		}
//		// Dropbox -> Dropbox
//		if (getExternalDrive(scrRes) != null
//				&& getExternalDrive(desRes) != null) {
//			Resource res = externalResourceService.getCurrentResourceByPath(
//					getExternalDrive(desRes), desPath);
//			return (res == null) ? false : true;
//		}
//		// default is mycollab -> mycollab , api can handle it
//		return false;
		return false;
	}

	/**
	 * @param scrRes
	 *            is MyCollab
	 * @param descRes
	 *            is Dropbox
	 * @param userMove
	 */

//	private void recursiveToMoveMyCollabToDropbox(Resource scrRes,
//			Resource descRes, String userMove) {
//		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
//				.getPath();
//		String desPath = desResourcePath + "/" + scrRes.getName();
//		if (scrRes instanceof Folder) {
//			Folder createdFolder = externalResourceService.createFolder(
//					getExternalDrive(descRes), desPath);
//			List<Resource> lstRes = resourceService.getResources(scrRes
//					.getPath());
//			if (lstRes != null) {
//				for (Resource res : lstRes) {
//					recursiveToMoveMyCollabToDropbox(res, createdFolder,
//							userMove);
//				}
//			}
//		} else if (scrRes instanceof Content) {
//			InputStream in = resourceService.getContentStream(scrRes.getPath());
//			scrRes.setPath(desPath);
//			externalResourceService.saveContent(getExternalDrive(descRes),
//					(Content) scrRes, in);
//		} else {
//			throw new MyCollabException("Not support, it' a strange file type");
//		}
//	}

	/**
	 * @param scrRes
	 *            is Drobox
	 * @param descRes
	 *            is Dropbox
	 * @param userMove
	 */
//	private void recursiveToMoveDropboxToDropbox(Resource scrRes,
//			Resource descRes, String userMove) {
//		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
//				.getPath();
//		String desPath = desResourcePath + "/" + scrRes.getName();
//		if (scrRes instanceof ExternalContent) {
//			InputStream in = externalResourceService.download(
//					getExternalDrive(scrRes), scrRes.getPath());
//			// reset Path of source upload
//			scrRes.setPath(desPath);
//			externalResourceService.saveContent(getExternalDrive(scrRes),
//					(Content) scrRes, in);
//		} else if (scrRes instanceof ExternalFolder) {
//			Folder createdFolder = externalResourceService.createFolder(
//					getExternalDrive(descRes), desPath);
//			List<Resource> lstRes = externalResourceService.getResources(
//					getExternalDrive(scrRes), scrRes.getPath());
//			if (lstRes != null) {
//				for (Resource res : lstRes) {
//					recursiveToMoveDropboxToDropbox(res, createdFolder,
//							userMove);
//				}
//			}
//		} else {
//			throw new MyCollabException("Not support, it' a strange file type");
//		}
//	}

	/**
	 * @param scrRes
	 *            is Drobox
	 * @param descRes
	 *            is MyCollab
	 * @param userMove
	 */
//	private void recursiveToMoveFolderDropboxToMyCollab(Resource scrRes,
//			Resource descRes, String userMove) {
//		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
//				.getPath();
//		String desPath = desResourcePath + "/" + scrRes.getName();
//		if (scrRes instanceof Content) {
//			InputStream in = externalResourceService.download(
//					getExternalDrive(scrRes), scrRes.getPath());
//			scrRes.setPath(desPath);
//			resourceService.saveContent((Content) scrRes, userMove, in);
//		} else if (scrRes instanceof Folder) {
//			Folder createdFolder = resourceService.createNewFolder(
//					descRes.getPath(), scrRes.getName(), userMove);
//			List<Resource> lstRes = externalResourceService.getResources(
//					getExternalDrive(scrRes), scrRes.getPath());
//			if (lstRes != null) {
//				for (Resource res : lstRes) {
//					recursiveToMoveFolderDropboxToMyCollab(res, createdFolder,
//							userMove);
//				}
//			}
//		} else {
//			throw new MyCollabException("Not support, it' a strange file type");
//		}
//	}
}
