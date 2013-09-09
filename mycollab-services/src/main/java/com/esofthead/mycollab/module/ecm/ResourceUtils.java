package com.esofthead.mycollab.module.ecm;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DropboxResourceService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class ResourceUtils {
	public static ExternalResourceService getExternalResourceService(
			String storageName) {
		if (StorageNames.DROPBOX.equals(storageName)) {
			return ApplicationContextUtil.getBean(DropboxResourceService.class);
		} else {
			throw new MyCollabException(
					"Current support only dropbox resource service");
		}
	}

	public static ExternalResourceService getExternalResourceService(
			ResourceType resourceType) {
		if (ResourceType.Dropbox == resourceType) {
			return ApplicationContextUtil.getBean(DropboxResourceService.class);
		} else {
			throw new MyCollabException(
					"Current support only dropbox resource service");
		}
	}

	public static ResourceType getType(Resource resource) {
		if (!(resource instanceof ExternalContent)
				&& !(resource instanceof ExternalFolder)) {
			return ResourceType.MyCollab;
		} else {
			try {
				String storageName = (String) PropertyUtils.getProperty(
						resource, "storageName");
				if (StorageNames.DROPBOX.equals(storageName)) {
					return ResourceType.Dropbox;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				throw new MyCollabException(
						"Can not define sotrage name of bean "
								+ BeanUtility.printBeanObj(resource));
			}
		}
	}
}
