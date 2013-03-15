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
	
	private static final String USER_NAME = "user";
	private static final String PASSWORD = "password";
	private static final String URL = "url";
	
	public static final void initContext(String userName, String password, String url) {
		prop.clear();
		prop.put(USER_NAME, userName);
		prop.put(PASSWORD, password);
		prop.put(URL, url);
	}
	
	private static final Properties prop = new Properties();

	public static List<Object> getData(String query, ISqlParser parser)
			throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(
				prop.getProperty(URL), prop);

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
