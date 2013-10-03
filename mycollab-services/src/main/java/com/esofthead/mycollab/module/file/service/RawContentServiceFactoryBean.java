package com.esofthead.mycollab.module.file.service;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.file.service.impl.AmazonRawContentServiceImpl;
import com.esofthead.mycollab.module.file.service.impl.FileRawContentServiceImpl;

@Service(value = "rawContentService")
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> implements IService {

	@Override
	protected RawContentService createInstance() throws Exception {
		try {
			if (SiteConfiguration.isSupportS3Storage()) {
				return new AmazonRawContentServiceImpl();
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
