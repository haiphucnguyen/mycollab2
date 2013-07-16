package com.esofthead.mycollab.module.ecm.service.impl;

import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.service.RawContentService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ContentJcrDao contentJcrDao;

	@Autowired
	private RawContentService rawContentService;

	@Override
	public List<Resource> getResources(String path) {
		return contentJcrDao.getResources(path);

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
		return folder;
	}

	@Override
	public void saveContent(Content content, String createdUser,
			InputStream refStream) {
		contentJcrDao.saveContent(content, createdUser);

		String contentPath = content.getPath();
		rawContentService.saveContent(contentPath, refStream);
	}

	@Override
	public void removeResource(String path) {
		contentJcrDao.removeResource(path);
		rawContentService.removeContent(path);
	}

	@Override
	public InputStream getContantStream(String path) {
		return rawContentService.getContent(path);
	}

	@Override
	public void rename(String oldPath, String newPath) {
		contentJcrDao.rename(oldPath, newPath);
		rawContentService.rename(oldPath, newPath);
	}

	@Override
	public List<Resource> searchResourcesByName(String baseFolderPath,
			String resourceName) {
		return contentJcrDao
				.searchResourcesByName(baseFolderPath, resourceName);
	}

	@Override
	public void moveResource(String oldPath, String destinationFolderPath) {
		String oldResourceName = oldPath.substring(
				oldPath.lastIndexOf("/") + 1, oldPath.length());
		try {
			if (!oldPath.substring(0, oldPath.lastIndexOf("/")).equals(
					destinationFolderPath)) {
				contentJcrDao.moveResource(oldPath, destinationFolderPath + "/"
						+ oldResourceName);
				rawContentService.moveContent(oldPath, destinationFolderPath
						+ "/" + oldResourceName);
			}
		} catch (MyCollabException e) {
			throw new MyCollabException(e.getMessage());
		}
	}
}
