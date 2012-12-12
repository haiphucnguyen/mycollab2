package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

public interface ContentService {
	void saveContent(Integer accountId, String objectPath, InputStream stream);

	InputStream getContent(Integer accountId, String objectPath);

	void removeContent(Integer accountId, String objectPath);
}
