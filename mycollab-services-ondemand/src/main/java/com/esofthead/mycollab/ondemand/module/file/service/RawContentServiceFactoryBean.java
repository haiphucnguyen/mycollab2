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
package com.esofthead.mycollab.ondemand.module.file.service;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.cache.IgnoreCacheClass;
import com.esofthead.mycollab.configuration.StorageManager;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.module.file.service.impl.FileRawContentServiceImpl;
import com.esofthead.mycollab.ondemand.module.file.service.impl.S3RawContentServiceImpl;

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be <code>FileRawContentServiceImpl</code> if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * <code>S3RawContentServiceImpl</code> if MyCollab is installed on MyCollab
 * server.
 * 
 */
@Service(value = "rawContentService")
@IgnoreCacheClass
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> implements IService {

	@Override
	protected RawContentService createInstance() throws Exception {
		try {
			if (StorageManager.isS3Storage()) {
				return new S3RawContentServiceImpl();
			} else {
				return new FileRawContentServiceImpl();
			}
		} catch (Exception e) {
			return new FileRawContentServiceImpl();
		}

	}

	@Override
	public Class<RawContentService> getObjectType() {
		return RawContentService.class;
	}

}
