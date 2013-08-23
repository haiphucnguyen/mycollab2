package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

import com.esofthead.mycollab.core.persistence.service.IService;

public interface ContentService extends IService {
	void saveContent(Integer accountId, String objectPath, InputStream stream);

	InputStream getContent(Integer accountId, String objectPath);

	void removeContent(Integer accountId, String objectPath);
}
