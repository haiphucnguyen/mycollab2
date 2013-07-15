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
import com.esofthead.mycollab.web.AppContext;

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
	public void moveResource(String oldPath, String destinationFolderPath,
			boolean isConfirmOverride) {
		try {
			Resource oldPathResource = contentJcrDao.getResource(oldPath);
			List<Resource> lstDesPathResource = contentJcrDao
					.getResources(destinationFolderPath);

			for (Resource res : lstDesPathResource) {
				int indexDuplicate = lstDesPathResource.indexOf(res);
				if (res.getName().equals(oldPathResource.getName())) {
					if (!isConfirmOverride)
						throw new MyCollabException(
								"Please check duplicated file/folder name .Do you want override?");
					contentJcrDao.removeResource(lstDesPathResource.get(
							indexDuplicate).getPath());
				}
			}

			oldPathResource.setPath(destinationFolderPath + "/"
					+ oldPathResource.getName());

			if (oldPathResource instanceof Folder)
				contentJcrDao.createFolder((Folder) oldPathResource,
						AppContext.getUsername());
			else if (oldPathResource instanceof Content)
				contentJcrDao.saveContent((Content) oldPathResource,
						AppContext.getUsername());

			recursiveMoveResource(oldPath, destinationFolderPath,
					oldPathResource.getName());

			contentJcrDao.removeResource(oldPath);

		} catch (Exception e) {
		}
	}

	private void recursiveMoveResource(String path, String destination,
			String parentFolderName) {
		List<Resource> lstResource = contentJcrDao.getResources(path);
		for (Resource res : lstResource) {
			if (res instanceof Folder) {
				recursiveMoveResource(res.getPath(), destination + "/"
						+ parentFolderName, res.getName());
			} else if (res instanceof Content) {
				res.setPath(destination + "/" + parentFolderName + "/"
						+ res.getName());
				contentJcrDao.saveContent((Content) res,
						AppContext.getUsername());
			}
		}
		Resource curFolder = contentJcrDao.getResource(path);
		curFolder.setPath(destination + "/" + curFolder.getName());

		contentJcrDao
				.createFolder((Folder) curFolder, AppContext.getUsername());
	}

}
