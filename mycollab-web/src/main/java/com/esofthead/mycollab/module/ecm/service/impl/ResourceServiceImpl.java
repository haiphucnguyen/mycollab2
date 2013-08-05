package com.esofthead.mycollab.module.ecm.service.impl;

import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLog;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogBuilder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.service.RawContentService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ContentJcrDao contentJcrDao;

	@Autowired
	private RawContentService rawContentService;

	@Autowired
	private ContentActivityLogService contentActivityLogService;

	@Override
	public List<Resource> getResources(String path) {
		return contentJcrDao.getResources(path);
	}

	@Override
	public List<Content> getContents(String path) {
		return contentJcrDao.getContents(path);
	}

	@Override
	public List<Folder> getSubFolders(String path) {
		return contentJcrDao.getSubFolders(path);
	}

	@Override
	public Folder createNewFolder(String baseFolderPath, String folderName,
			String createdBy) {
		Folder folder = new Folder();
		String folderPath = baseFolderPath + "/" + folderName;
		folder.setPath(folderPath);
		folder.setCreatedBy(createdBy);
		folder.setCreated(new GregorianCalendar());
		contentJcrDao.createFolder(folder, createdBy);
		ContentActivityLog activityLog = new ContentActivityLog();
		ContentActivityLogAction createFolderAction = ContentActivityLogBuilder
				.makeCreateFolder(folderPath);
		activityLog.setCreateduser(createdBy);
		activityLog.setActiondesc(createFolderAction.toString());
		contentActivityLogService.saveWithSession(activityLog, "");
		return folder;
	}

	@Override
	public void saveContent(Content content, String createdUser,
			InputStream refStream) {
		contentJcrDao.saveContent(content, createdUser);

		String contentPath = content.getPath();
		rawContentService.saveContent(contentPath, refStream);

		ContentActivityLog activityLog = new ContentActivityLog();
		ContentActivityLogAction createContentAction = ContentActivityLogBuilder
				.makeCreateContent(contentPath);
		activityLog.setCreateduser(createdUser);
		activityLog.setActiondesc(createContentAction.toString());
		contentActivityLogService.saveWithSession(activityLog, "");
	}

	@Override
	public void removeResource(String path, String deleteUser) {
		Resource res = contentJcrDao.getResource(path);
		ContentActivityLogAction deleteResourceAction;
		if (res instanceof Folder)
			deleteResourceAction = ContentActivityLogBuilder
					.makeDeleteFolder(path);
		else
			deleteResourceAction = ContentActivityLogBuilder
					.makeDeleteContent(path);

		contentJcrDao.removeResource(path);
		rawContentService.removeContent(path);

		ContentActivityLog activityLog = new ContentActivityLog();
		activityLog.setCreateduser(deleteUser);
		activityLog.setActiondesc(deleteResourceAction.toString());
		contentActivityLogService.saveWithSession(activityLog, "");
	}

	@Override
	public InputStream getContentStream(String path) {
		return rawContentService.getContent(path);
	}

	@Override
	public void rename(String oldPath, String newPath, String userUpdate) {
		Resource res = contentJcrDao.getResource(oldPath);
		ContentActivityLogAction renameAction;
		if (res instanceof Folder)
			renameAction = ContentActivityLogBuilder.makeRenameFolder(oldPath,
					newPath);
		else
			renameAction = ContentActivityLogBuilder.makeRenameContent(oldPath,
					newPath);

		contentJcrDao.rename(oldPath, newPath);
		rawContentService.rename(oldPath, newPath);

		ContentActivityLog activityLog = new ContentActivityLog();
		activityLog.setCreateduser(userUpdate);
		activityLog.setActiondesc(renameAction.toString());
		contentActivityLogService.saveWithSession(activityLog, "");
	}

	@Override
	public List<Resource> searchResourcesByName(String baseFolderPath,
			String resourceName) {
		return contentJcrDao
				.searchResourcesByName(baseFolderPath, resourceName);
	}

	@Override
	public void moveResource(String oldPath, String destinationFolderPath,
			String userMove) {
		String oldResourceName = oldPath.substring(
				oldPath.lastIndexOf("/") + 1, oldPath.length());

		Resource oldResource = contentJcrDao.getResource(oldPath);
		ContentActivityLogAction moveResoureAction;
		if (oldResource instanceof Folder) {
			moveResoureAction = ContentActivityLogBuilder.makeMoveFolder(
					oldPath, destinationFolderPath);
		} else {
			moveResoureAction = ContentActivityLogBuilder.makeMoveContent(
					oldPath, destinationFolderPath);
		}
		if ((oldResource instanceof Folder)
				&& destinationFolderPath.contains(oldPath)) {
			throw new UserInvalidInputException(
					"Can not move asset(s) to folder " + destinationFolderPath);
		} else {
			String destinationPath = destinationFolderPath + "/"
					+ oldResourceName;
			contentJcrDao.moveResource(oldPath, destinationPath);
			rawContentService.moveContent(oldPath, destinationPath);

			ContentActivityLog activityLog = new ContentActivityLog();
			activityLog.setCreateduser(userMove);
			activityLog.setActiondesc(moveResoureAction.toString());
			contentActivityLogService.saveWithSession(activityLog, "");
		}
	}

	@Override
	public Folder getParentFolder(String path) {
		try {
			String parentPath = path.substring(0, path.lastIndexOf("/"));
			Resource res = contentJcrDao.getResource(parentPath);
			if (res instanceof Folder)
				return (Folder) res;
			return null;
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public Resource getResource(String path) {
		return contentJcrDao.getResource(path);
	}
}
