package com.esofthead.mycollab.module.ecm.service;

import java.io.InputStream;
import java.util.List;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

public interface ExternalResourceService extends IService {
	/**
	 * 
	 * @param drive
	 * @param path
	 * @return
	 */
	List<Resource> getResources(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param path
	 * @return
	 */
	List<ExternalFolder> getSubFolders(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param path
	 * @return
	 */
	Resource getCurrentResourceByPath(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param childPath
	 * @return
	 */
	Folder getParentResourceFolder(ExternalDrive drive, String childPath);

	/**
	 * 
	 * @param drive
	 * @param path
	 * @return
	 */
	Folder createFolder(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param content
	 * @param in
	 */
	void saveContent(ExternalDrive drive, Content content, InputStream in);

	/**
	 * 
	 * @param drive
	 * @param oldPath
	 * @param newPath
	 */
	void rename(ExternalDrive drive, String oldPath, String newPath);

	/**
	 * 
	 * @param drive
	 * @param path
	 */
	void deleteResource(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param path
	 * @return
	 */
	InputStream download(ExternalDrive drive, String path);

	/**
	 * 
	 * @param drive
	 * @param fromPath
	 * @param toPath
	 */
	void move(ExternalDrive drive, String fromPath, String toPath);
}
