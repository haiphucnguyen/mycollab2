package com.esofthead.mycollab.module.ecm;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.service.DropboxResourceService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class ExternalResourceUtils {
	public static ExternalResourceService getExternalResourceService(
			String storageName) {
		if (StorageNames.DROPBOX.equals(storageName)) {
			return ApplicationContextUtil.getBean(DropboxResourceService.class);
		} else {
			throw new MyCollabException(
					"Current support only dropbox resource service");
		}
	}
}
