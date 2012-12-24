package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

@Service(value = "rawContentService")
public class RawContentServiceFactoryBean extends
		AbstractFactoryBean<RawContentService> {

	private static Logger log = LoggerFactory
			.getLogger(RawContentServiceFactoryBean.class);

	@SuppressWarnings("unchecked")
	@Override
	protected RawContentService createInstance() throws Exception {
		InputStream stream = RawContentServiceFactoryBean.class.getClassLoader()
				.getResourceAsStream("resources.properties");
		if (stream != null) {
			Properties props = new Properties();
			props.load(stream);

			String rawContentImplClassName = props
					.getProperty("content.rawContentServiceImpl",
							"com.esofthead.mycollab.module.file.service.impl.RawContentServiceImpl");
			Class<RawContentService> cls = (Class<RawContentService>) Class
					.forName(rawContentImplClassName);
			RawContentService rawContentService = cls.newInstance();
			return rawContentService;

		} else {
			log.error("Can not find resource file resources.properties in class path");
			return null;
		}
	}

	@Override
	public Class<?> getObjectType() {
		return RawContentService.class;
	}

}
