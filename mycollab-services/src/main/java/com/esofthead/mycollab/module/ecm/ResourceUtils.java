/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.ecm;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DropboxResourceService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class ResourceUtils {
	public static ExternalResourceService getExternalResourceService(
			String storageName) {
		if (StorageNames.DROPBOX.equals(storageName)) {
			return ApplicationContextUtil.getSpringBean(DropboxResourceService.class);
		} else {
			throw new MyCollabException(
					"Current support only dropbox resource service");
		}
	}

	public static ExternalResourceService getExternalResourceService(
			ResourceType resourceType) {
		if (ResourceType.Dropbox == resourceType) {
			return ApplicationContextUtil.getSpringBean(DropboxResourceService.class);
		} else {
			throw new MyCollabException(
					"Current support only dropbox resource service");
		}
	}

	public static ExternalDrive getExternalDrive(Resource res) {
		if (res instanceof ExternalFolder) {
			return ((ExternalFolder) res).getExternalDrive();
		} else if (res instanceof ExternalContent) {
			return ((ExternalContent) res).getExternalDrive();
		}
		return null;
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
