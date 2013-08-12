/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.test;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class DataSourceFactoryBean extends AbstractFactoryBean {

	private static Logger log = LoggerFactory
			.getLogger(DataSourceFactoryBean.class);

	private BasicDataSource dataSource;

	public DataSource getDataSource() {
		try {
			return (DataSource) createInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Object createInstance() throws Exception {
		InputStream stream = DataSourceFactoryBean.class.getClassLoader()
				.getResourceAsStream("resources.properties");
		if (stream == null) {
			stream = DataSourceFactoryBean.class.getClassLoader()
					.getResourceAsStream("default-resources.properties");
		}

		Properties props = new Properties();
		if (stream != null) {
			props.load(stream);
		}

		log.debug("Use database settings is: " + props);

		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(props.getProperty("db.driverClassName",
				"com.mysql.jdbc.Driver"));
		dataSource.setUrl(props.getProperty("db.url",
				"jdbc:mysql://localhost/mycollab_test?autoReconnect=true"));
		dataSource.setUsername(props.getProperty("db.username", "root"));
		dataSource
				.setPassword(props.getProperty("db.password", "esofthead321"));
		return dataSource;
	}

	@Override
	public void destroy() throws Exception {
		super.destroy();

		if (dataSource != null) {
			dataSource.close();
			log.debug("Close connection");
		}
		
		
	}

	@Override
	public Class getObjectType() {
		return DataSource.class;
	}

}
