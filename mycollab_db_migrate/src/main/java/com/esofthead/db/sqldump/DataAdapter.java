package com.esofthead.db.sqldump;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import java.sql.Connection;
import java.sql.Statement;

public class DataAdapter {
	// private static final String USER_NAME = "db.username";
	// private static final String PASSWORD = "db.password";
	// private static final String URL = "db.url";
	//
	// private static final String RESOURCE_PROPERTIES = "config.properties";
	// private static Properties properties;
	// static {
	// properties = new Properties();
	// try {
	// properties.load(Thread.currentThread().getContextClassLoader()
	// .getResourceAsStream(RESOURCE_PROPERTIES));
	// } catch (Exception e) {
	// properties = null;
	// e.printStackTrace();
	// }
	// }
	//
	// private static String getProperty(String key) {
	// if (null != properties)
	// return properties.getProperty(key);
	// return null;
	// }

	public static List<Object> getData(String query, ISqlParser parser)
			throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Properties prop = new Properties();
		prop.put("user", DbConfiguration
				.getProperty(DbConfiguration.USER_NAME));
		prop.put("password", DbConfiguration
				.getProperty(DbConfiguration.PASSWORD));
		Connection con = (Connection) DriverManager.getConnection(
				DbConfiguration
						.getProperty(DbConfiguration.URL), prop);

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Object> lsResult = new LinkedList<Object>();
		while (rs.next()) {
			Object obj = parser.parse(rs);
			if (null != obj)
				lsResult.add(obj);
		}
		con.close();
		return lsResult;
	}

}
