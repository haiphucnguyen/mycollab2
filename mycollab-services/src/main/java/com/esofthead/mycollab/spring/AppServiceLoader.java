package com.esofthead.mycollab.spring;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.esofthead.mycollab.core.utils.FileUtils;
import com.fasterxml.jackson.core.TreeNode;

@Configuration
public class AppServiceLoader {
	@Bean(name = "myCollabProperties")
	public static PropertySourcesPlaceholderConfigurer properties() {
		TreeNode a;
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

		Resource[] resources;
		File myCollabResourceFile = FileUtils
				.detectFileByName(new File(System.getProperty("user.dir")),
						"mycollab.properties");
		if (myCollabResourceFile != null) {
			resources = new Resource[] { new FileSystemResource(
					myCollabResourceFile) };
		} else {
			resources = new Resource[] { new ClassPathResource(
					"mycollab.properties") };
		}

		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}
}
