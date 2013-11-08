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
