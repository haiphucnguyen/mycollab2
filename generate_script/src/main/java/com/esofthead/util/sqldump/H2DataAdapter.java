package com.esofthead.util.sqldump;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2DataAdapter {
	public static void executeNonQuery(String query) throws Exception {
		Class.forName(ApplicationConfigurations
				.getProperty(ApplicationConfigurations.H2_DRIVER));
		Connection con = (Connection) DriverManager.getConnection(
				ApplicationConfigurations.getProperty(ApplicationConfigurations.H2_URL));
		Statement stmt = (Statement) con.createStatement();
		stmt.execute(query);
		con.close();
	}
}
