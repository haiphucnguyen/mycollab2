package com.esofthead.mycollab.module.file.service;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.esofthead.mycollab.module.file.service.impl.AmazonService;

@Service(value = "rawContentService")
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> {

	@SuppressWarnings("unchecked")
	@Override
	protected RawContentService createInstance() throws Exception {
		if (StorageSetting.isFileStorage()) {
			String rawContentImplClassName = ApplicationProperties
					.getString("content.rawContentServiceImpl",
							"com.esofthead.mycollab.module.file.service.impl.RawContentServiceImpl");
			Class<RawContentService> cls = (Class<RawContentService>) Class
					.forName(rawContentImplClassName);
			RawContentService rawContentService = cls.newInstance();
			return rawContentService;
		} else if (StorageSetting.isS3Storage()) {
			return new AmazonService();
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
