package com.esofthead.mycollab.test.module;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;

import com.esofthead.mycollab.test.DataSourceFactoryBean;

public class DbUnitUtil {

    public static void main(String[] args) throws Exception {
        DataSource dataSource = (DataSource) new DataSourceFactoryBean()
                .getDataSource();
        File file = new File("src/test/resources/engroup.dtd");
        IDatabaseConnection connection = null;
        try {
            connection = new DatabaseDataSourceConnection(dataSource);
            connection.getConfig().setProperty(
                    DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
            // write DTD file
            FlatDtdDataSet.write(connection.createDataSet(),
                    new FileOutputStream(file));

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
    }
}
