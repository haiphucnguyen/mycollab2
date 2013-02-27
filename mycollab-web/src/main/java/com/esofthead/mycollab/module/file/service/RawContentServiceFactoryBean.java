package com.esofthead.mycollab.module.file.service;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;

@Service(value = "rawContentService")
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> {

	@SuppressWarnings("unchecked")
	@Override
	protected RawContentService createInstance() throws Exception {
		String rawContentImplClassName = ApplicationProperties
				.getProperty("content.rawContentServiceImpl",
						"com.esofthead.mycollab.module.file.service.impl.RawContentServiceImpl");
		Class<RawContentService> cls = (Class<RawContentService>) Class
				.forName(rawContentImplClassName);
		RawContentService rawContentService = cls.newInstance();
		return rawContentService;
	}

	@Override
	public Class<?> getObjectType() {
		return RawContentService.class;
	}

}
