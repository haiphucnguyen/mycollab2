package com.esofthead.mycollab.module.ecm.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceMover;
import com.esofthead.mycollab.module.ecm.service.ResourceService;

@Service
public class ResourceMoverImpl implements ResourceMover {

	@Autowired
	private ExternalResourceService externalResourceService;

	@Autowired
	private ResourceService resourceService;

	/**
	 * show what type of Dirve {0 : is MyCollab} {1 : Dropbox} {2 : GoogleDrive}
	 */
	private int srcTypeDrive;
	private int desTypeDrive;

	/**
	 * descRes must be instanceof Folder
	 */
	@Override
	public void moveResource(Resource scrRes, Resource descRes, String userMove) {
		// get Instance of Resouce------------------------------
		srcTypeDrive = getTypeOfResource(scrRes);
		desTypeDrive = getTypeOfResource(descRes);
		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();
		String scrPath = scrRes.getPath();
		try {
			if (descRes instanceof Content)
				throw new MyCollabException(
						"You cant move somethings to content path.That is impossible.");

			// MyCollab -> MyCollab : implemented
			if (srcTypeDrive == 0 && desTypeDrive == 0) {
				resourceService.moveResource(scrRes.getPath(),
						descRes.getPath(), userMove);
			} else if (srcTypeDrive == 1 && desTypeDrive == 1) {// Dropbox ->
																// Dropbox
				// in once Dropbox : DbxClient move
				ExternalDrive srcDrive = getExternalDrive(scrRes);
				ExternalDrive desDrive = getExternalDrive(descRes);

				// in the same dropbox
				if (srcDrive.getAccesstoken().equals(desDrive.getAccesstoken())) {
					if (checkDuplicateName(scrRes, descRes))
						throw new MyCollabException(
								"Duplicate file/name, please check it before move!");
					externalResourceService.move(srcDrive, scrRes.getPath(),
							desPath);
				} else { // Dropbox -> another Dropbox
					if (checkDuplicateName(scrRes, descRes))
						throw new MyCollabException(
								"Duplicate file/name, please check it before move!");

					if (scrRes instanceof ExternalContent) {
						InputStream in = externalResourceService.download(
								srcDrive, scrRes.getPath());
						// reset Path of source upload
						scrRes.setPath(desPath);
						externalResourceService.saveContent(desDrive,
								(Content) scrRes, in);
					} else if (scrRes instanceof ExternalFolder) {
						Folder createdFolder = externalResourceService
								.createFolder(desDrive, desPath);
						List<Resource> lstRes = externalResourceService
								.getResources(srcDrive, scrRes.getPath());
						for (Resource res : lstRes) {
							recursiveToMoveDropboxToDropbox(res, createdFolder,
									userMove);
						}
					} else {
						throw new MyCollabException(
								"Not support, it' a strange file type");
					}
					externalResourceService.deleteResource(srcDrive, scrPath);
				}
			} else { // MyCollab -> Dropbox : uploader
				if (checkDuplicateName(scrRes, descRes))
					throw new MyCollabException(
							"Duplicate file/name, please check it before move!");

				if (srcTypeDrive == 0 && desTypeDrive == 1) {
					if (scrRes instanceof Folder) {
						Folder createdFolder = externalResourceService
								.createFolder(getExternalDrive(descRes),
										desPath);
						List<Resource> lstRes = resourceService
								.getResources(scrRes.getPath());
						if (lstRes != null) {
							for (Resource res : lstRes) {
								recursiveToMoveMyCollabToDropbox(res,
										createdFolder, userMove);
							}
						}
					} else if (scrRes instanceof Content) {
						InputStream in = resourceService
								.getContentStream(scrRes.getPath());
						scrRes.setPath(desPath);
						externalResourceService
								.saveContent(getExternalDrive(descRes),
										(Content) scrRes, in);
					} else {
						throw new MyCollabException(
								"Not support, it' a strange file type");
					}
					resourceService.removeResource(scrPath, userMove);
				} else { // Dropbox -> MyCollab : createNewFolder & saveContent
					if (checkDuplicateName(scrRes, descRes))
						throw new MyCollabException(
								"Duplicate file/name, please check it before move!");

					if (scrRes instanceof Content) {
						InputStream in = externalResourceService.download(
								getExternalDrive(scrRes), scrRes.getPath());

						scrRes.setPath(desPath);
						resourceService.saveContent((Content) scrRes, userMove,
								in);
					} else if (scrRes instanceof Folder) {
						Folder createdFolder = resourceService.createNewFolder(
								descRes.getPath(), scrRes.getName(), userMove);
						List<Resource> lstRes = externalResourceService
								.getResources(getExternalDrive(scrRes),
										scrRes.getPath());
						if (lstRes != null) {
							for (Resource res : lstRes) {
								recursiveToMoveFolderDropboxToMyCollab(res,
										createdFolder, userMove);
							}
						}
					} else {
						throw new MyCollabException(
								"Not support, it' a strange file type");
					}
					externalResourceService.deleteResource(
							getExternalDrive(scrRes), scrPath);
				}
			}
			// TODO : more handle for GoogleDrive here
		} catch (Exception e) {
			throw new MyCollabException(e);
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
	private boolean checkDuplicateName(Resource scrRes, Resource desRes) {
		String desResourcePath = (desRes.getPath().equals("/")) ? "" : desRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();
		// Dropbox -> MyCollab
		if (getExternalDrive(scrRes) != null
				&& getExternalDrive(desRes) == null) {
			Resource res = resourceService.getResource(desPath);
			return (res == null) ? false : true;
		}
		// MyCollab -> Dropbox
		if (getExternalDrive(scrRes) == null
				&& getExternalDrive(desRes) != null) {
			Resource res = externalResourceService.getcurrentResourceByPath(
					getExternalDrive(desRes), desPath);
			return (res == null) ? false : true;
		}
		// Dropbox -> Dropbox
		if (getExternalDrive(scrRes) != null
				&& getExternalDrive(desRes) != null) {
			Resource res = externalResourceService.getcurrentResourceByPath(
					getExternalDrive(desRes), desPath);
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

	private void recursiveToMoveMyCollabToDropbox(Resource scrRes,
			Resource descRes, String userMove) {
		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();
		if (scrRes instanceof Folder) {
			Folder createdFolder = externalResourceService.createFolder(
					getExternalDrive(descRes), desPath);
			List<Resource> lstRes = resourceService.getResources(scrRes
					.getPath());
			if (lstRes != null) {
				for (Resource res : lstRes) {
					recursiveToMoveMyCollabToDropbox(res, createdFolder,
							userMove);
				}
			}
		} else if (scrRes instanceof Content) {
			InputStream in = resourceService.getContentStream(scrRes.getPath());
			scrRes.setPath(desPath);
			externalResourceService.saveContent(getExternalDrive(descRes),
					(Content) scrRes, in);
		} else {
			throw new MyCollabException("Not support, it' a strange file type");
		}
	}

	/**
	 * @param scrRes
	 *            is Drobox
	 * @param descRes
	 *            is Dropbox
	 * @param userMove
	 */
	private void recursiveToMoveDropboxToDropbox(Resource scrRes,
			Resource descRes, String userMove) {
		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();
		if (scrRes instanceof ExternalContent) {
			InputStream in = externalResourceService.download(
					getExternalDrive(scrRes), scrRes.getPath());
			// reset Path of source upload
			scrRes.setPath(desPath);
			externalResourceService.saveContent(getExternalDrive(scrRes),
					(Content) scrRes, in);
		} else if (scrRes instanceof ExternalFolder) {
			Folder createdFolder = externalResourceService.createFolder(
					getExternalDrive(descRes), desPath);
			List<Resource> lstRes = externalResourceService.getResources(
					getExternalDrive(scrRes), scrRes.getPath());
			if (lstRes != null) {
				for (Resource res : lstRes) {
					recursiveToMoveDropboxToDropbox(res, createdFolder,
							userMove);
				}
			}
		} else {
			throw new MyCollabException("Not support, it' a strange file type");
		}
	}

	/**
	 * @param scrRes
	 *            is Drobox
	 * @param descRes
	 *            is MyCollab
	 * @param userMove
	 */
	private void recursiveToMoveFolderDropboxToMyCollab(Resource scrRes,
			Resource descRes, String userMove) {
		String desResourcePath = (descRes.getPath().equals("/")) ? "" : descRes
				.getPath();
		String desPath = desResourcePath + "/" + scrRes.getName();
		if (scrRes instanceof Content) {
			InputStream in = externalResourceService.download(
					getExternalDrive(scrRes), scrRes.getPath());
			scrRes.setPath(desPath);
			resourceService.saveContent((Content) scrRes, userMove, in);
		} else if (scrRes instanceof Folder) {
			Folder createdFolder = resourceService.createNewFolder(
					descRes.getPath(), scrRes.getName(), userMove);
			List<Resource> lstRes = externalResourceService.getResources(
					getExternalDrive(scrRes), scrRes.getPath());
			if (lstRes != null) {
				for (Resource res : lstRes) {
					recursiveToMoveFolderDropboxToMyCollab(res, createdFolder,
							userMove);
				}
			}
		} else {
			throw new MyCollabException("Not support, it' a strange file type");
		}
	}

	private ExternalDrive getExternalDrive(Resource res) {
		if (res instanceof ExternalFolder)
			return ((ExternalFolder) res).getExternalDrive();
		else if (res instanceof ExternalContent)
			return ((ExternalContent) res).getExternalDrive();
		else
			return null;
	}

	private int getTypeOfResource(Resource res) {
		if (res instanceof Folder) {
			if (res instanceof ExternalFolder) { // Dropbox
				return 1;
			}
			// because not instanceof GoogleDrive yet!!!
			return 0;
		} else {
			if (res instanceof ExternalContent) { // Dropbox
				return 1;
			}
			return 0;
		}
	}
}
