package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

public interface RawContentService {
	void saveContent(String objectPath, InputStream stream);

	InputStream getContent(String objectPath);

	void removeContent(String objectPath);

	void rename(String oldPath, String newName);

	void moveContent(String oldPath, String destinationPath);
}