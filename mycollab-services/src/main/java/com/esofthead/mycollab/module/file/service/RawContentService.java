package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface RawContentService extends IService {
	void saveContent(String objectPath, InputStream stream);

	InputStream getContentStream(String objectPath);

	void removeContent(String objectPath);

	void rename(String oldPath, String newPath);

	void moveContent(String oldPath, String destinationPath);
	
	long getSize(String path);
	
	
}