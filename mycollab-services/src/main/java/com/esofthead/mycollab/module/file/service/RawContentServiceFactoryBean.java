package com.esofthead.mycollab.module.file.service;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.service.impl.AmazonRawContentServiceImpl;
import com.esofthead.mycollab.module.file.service.impl.FileRawContentServiceImpl;

@Service(value = "rawContentService")
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> {

	@Override
	protected RawContentService createInstance() throws Exception {
		if (SiteConfiguration.isSupportFileStorage()) {
			RawContentService rawContentService = new FileRawContentServiceImpl();
			return rawContentService;
		} else if (SiteConfiguration.isSupportS3Storage()) {
			return new AmazonRawContentServiceImpl();
		} else {
			throw new MyCollabException(
					"Do not support storage system setting. Accept file or s3 only");
		}

	}

	@Override
	public Class<RawContentService> getObjectType() {
		return RawContentService.class;
	}

}
