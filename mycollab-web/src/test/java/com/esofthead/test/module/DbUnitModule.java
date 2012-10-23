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
package com.esofthead.test.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.test.DataSourceFactoryBean;
import com.esofthead.test.TestException;

public final class DbUnitModule extends AbstractEngroupTestModule {

	private DataSource dataSource;

	private static Logger log = LoggerFactory.getLogger(DbUnitModule.class);

	private IDatabaseTester databaseTester;

	public void setUp() {
		try {
			this.dataSource = (DataSource) new DataSourceFactoryBean()
					.getDataSource();
		} catch (Exception e1) {
			throw new RuntimeException("Error while getting data source", e1);
		}
		

		// Load dataset from xml file
		IDataSet dataSet = null;
		String classFileName = this.host.getName();
		String xmlFile = classFileName.replace('.', '/') + ".xml";

		InputStream stream = null;
		String xmlFullPath = "src/test/resources/" + xmlFile;
		if (new File(xmlFullPath).exists()) {
			try {
				stream = new FileInputStream(xmlFullPath);
			} catch (FileNotFoundException e) {
				throw new TestException(e);
			}
		} else {
			stream = getClass().getClassLoader().getResourceAsStream(xmlFile);
		}
		Assert.assertNotNull("Can not find resource " + xmlFile
				+ ". Stream is " + stream, stream);

		try {
			FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();
			dataSetBuilder.setCaseSensitiveTableNames(true);
			dataSetBuilder.setMetaDataSetFromDtd(DbUnitModule.class
					.getClassLoader().getResourceAsStream("engroup.dtd"));
			dataSet = dataSetBuilder.build(stream);
		} catch (Exception e) {
			throw new TestException(e);
		}

		try {
			this.databaseTester = new DataSourceDatabaseTester(this.dataSource);
			this.databaseTester
					.getConnection()
					.getConfig()
					.setProperty(
							DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES,
							true);
			this.databaseTester
					.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
			this.databaseTester.setDataSet(dataSet);
			this.databaseTester.onSetup();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		log.debug("Finish test setup");
	}

	/*private void initDatabase(DataSource ds) {
		DataSourceMigrationManager migrationManager = new DataSourceMigrationManager(
				ds);
		ResourceMigrationResolver resolver = new ResourceMigrationResolver(
				"classpath:/dbscripts/");
		migrationManager.setMigrationResolver(resolver);
		migrationManager.migrate();
	}*/

	public void tearDown() {
		try {
			this.databaseTester.onTearDown();
		} catch (Exception e) {
			throw new TestException(e);
		}

	}
}
