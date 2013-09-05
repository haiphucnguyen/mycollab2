package com.esofthead.mycollab.module.ecm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * show what type of Dirve 0 : is MyCollab 1 : Dropbox 2 : GoogleDrive
	 */
	private int srcTypeDrive;
	private int desTypeDrive;

	@Override
	public void moveResource(Resource scrRes, Resource descRes, String userMove) {
		// get Instance of Resouce------------------------------
		srcTypeDrive = getTypeOfResource(scrRes);
		desTypeDrive = getTypeOfResource(descRes);
		// MyCollab -> MyCollab : implemented
		if (srcTypeDrive == 0 && desTypeDrive == 0) {
			resourceService.moveResource(scrRes.getPath(), descRes.getPath(),
					userMove);
		} else if (srcTypeDrive == 1 && desTypeDrive == 1) {// Dropbox ->
															// Dropbox
			// in once Dropbox : DbxClient move
			ExternalDrive srcDrive = getExternalDrive(scrRes);
			ExternalDrive desDrive = getExternalDrive(descRes);

			if (srcDrive.getAccesstoken() == srcDrive.getAccesstoken()) {
				// externalResourceService.move(drive, fromPath, toPath)
			} else {

			}

			// Dropbox -> another Dropbox

		} else { // Mycollab <-> Dropbox
					// MyCollab -> Dropbox : uploader
			if (srcTypeDrive == 0 && desTypeDrive == 1) {

			} else { // MyCollab <- Dropbox : createNewFolder & saveContent

			}
		}
		// TODO : more handle for GoogleDrive here
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
			// becase not instanceof GoogleDrive yet!!!
			return 0;
		} else {
			if (res instanceof ExternalContent) { // Dropbox
				return 1;
			}
			return 0;
		}
	}
}
