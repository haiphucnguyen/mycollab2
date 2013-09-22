package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface RawContentService extends IService {
	void saveContent(String objectPath, InputStream stream);

	InputStream getContentStream(String objectPath);

	void removePath(String objectPath);

	void renamePath(String oldPath, String newPath);

	void movePath(String oldPath, String destinationPath);
	
	long getSize(String path);
	
}