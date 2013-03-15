package com.esofthead.db.sqldump;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2DataAdapter {
	public static void executeNonQuery(String query) throws Exception {
		Class.forName(DbConfiguration
				.getProperty(DbConfiguration.H2_DRIVER));
		Connection con = (Connection) DriverManager.getConnection(
				DbConfiguration.getProperty(DbConfiguration.H2_URL));
		Statement stmt = (Statement) con.createStatement();
		stmt.execute(query);
		con.close();
	}
}
