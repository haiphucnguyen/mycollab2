package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

import com.esofthead.mycollab.core.dist.NotMobile;
import com.esofthead.mycollab.core.persistence.service.IService;

public interface ContentService extends IService {
	@NotMobile
	void saveContent(Integer accountId, String objectPath, InputStream stream);

	@NotMobile
	InputStream getContent(Integer accountId, String objectPath);

	void removeContent(Integer accountId, String objectPath);
}
