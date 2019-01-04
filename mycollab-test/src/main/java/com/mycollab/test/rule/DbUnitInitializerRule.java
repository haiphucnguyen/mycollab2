/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.test.rule;

import com.mycollab.test.DataSet;
import com.mycollab.test.DbConfiguration;
import com.mycollab.test.TestException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author MyCollab Ltd.
 * @since 7.0.0
 */
public class DbUnitInitializerRule implements BeforeEachCallback, AfterEachCallback {
    private static final Logger LOG = LoggerFactory.getLogger(DbUnitInitializerRule.class);

    private IDatabaseTester databaseTester;

    private static JdbcDatabaseContainer dbContainer;

    static {
        dbContainer = new MySQLContainer();

        dbContainer.start();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();
        if (requiredTestMethod.getAnnotation(DataSet.class) != null) {
            setUp(extensionContext.getRequiredTestClass());
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (databaseTester != null) {
            try {
                databaseTester.onTearDown();
            } catch (Exception e) {
                throw new TestException(e);
            }
        }
    }

    private void setUp(Class<?> testClass) {
        // Load dataset from xml file
        IDataSet dataSet;
        String classFileName = testClass.getName();
        String xmlFile = classFileName.replace('.', '/') + ".xml";

        InputStream stream;
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

        try {
            FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();
            dataSetBuilder.setCaseSensitiveTableNames(true);
            dataSetBuilder.setMetaDataSetFromDtd(DbUnitInitializerRule.class.getClassLoader().getResourceAsStream("mycollab.dtd"));
            dataSet = dataSetBuilder.build(stream);
        } catch (Exception e) {
            throw new TestException(e);
        }

        try {
            DbConfiguration dbConf = new DbConfiguration(dbContainer.getDriverClassName(), dbContainer.getJdbcUrl(),
                    dbContainer.getUsername(), dbContainer.getPassword());
            databaseTester = new DbUnitTester(dbConf.getDriverCls(), dbConf.getJdbcUrl(), dbConf.getUsername(),
                    dbConf.getPassword(), "test");
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LOG.debug("Finish test setup");
    }

    private static class DbUnitTester extends JdbcDatabaseTester {
        DbUnitTester(String driverClass, String connectionUrl, String username, String password, String schema) throws ClassNotFoundException {
            super(driverClass, connectionUrl, username, password,schema);
        }

        public IDatabaseConnection getConnection() throws Exception {
            IDatabaseConnection connection = super.getConnection();
            DatabaseConfig config = connection.getConfig();
            config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, false);
            config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            return connection;
        }
    }

    public static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + dbContainer.getJdbcUrl(),
                    "spring.datasource.username=" + dbContainer.getUsername(),
                    "spring.datasource.password=" + dbContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
