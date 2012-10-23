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
package com.esofthead.test;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class DataSourceFactoryBean extends AbstractFactoryBean {

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
				.getResourceAsStream("database.properties");
		if (stream == null) {
			stream = DataSourceFactoryBean.class.getClassLoader()
					.getResourceAsStream("default-database.properties");
		}

		if (stream == null) {
			throw new RuntimeException(
					"Can not seek the database property configurations.");
		} else {
			Properties props = new Properties();
			props.load(stream);

			System.out.println("Use database settings is: " + props);

			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(props
					.getProperty("db.driverClassName"));
			dataSource.setUrl(props.getProperty("db.url"));
			dataSource.setUsername(props.getProperty("db.username"));
			dataSource.setPassword(props.getProperty("db.password"));
			return dataSource;
		}
	}

	@Override
	public Class getObjectType() {
		return DataSource.class;
	}

}
