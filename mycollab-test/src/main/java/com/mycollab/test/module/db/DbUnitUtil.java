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
package com.mycollab.test.module.db;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class DbUnitUtil {

    public static void main(String[] args) throws Exception {
        Connection dbCon = DriverManager.getConnection("jdbc:mysql://localhost/mycollab?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&rewriteBatchedStatements=true&useCompression=true&useServerPrepStmts=false&verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true", "hai", "mycollab123");
        File file = new File("src/main/resources/mycollab.dtd");
        IDatabaseConnection connection = new DatabaseConnection(dbCon);
        connection.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream(file));
    }
}
